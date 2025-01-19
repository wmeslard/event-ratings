package adeo.leroymerlin.cdp;

import adeo.leroymerlin.cdp.dto.EventDto;
import adeo.leroymerlin.cdp.entity.Band;
import adeo.leroymerlin.cdp.entity.Event;
import adeo.leroymerlin.cdp.entity.Member;
import adeo.leroymerlin.cdp.exception.EventNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static adeo.leroymerlin.cdp.TestUtils.readJsonResource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    void shouldReturnsAllEvents() throws IOException {
        // GIVEN
        Event event = readJsonResource("/seed/event.json", Event.class);
        when(eventRepository.findAll()).thenReturn(List.of(event));

        // WHEN
        List<EventDto> events = eventService.getEvents();

        // THEN
        assertThat(events).hasSize(1);
        verify(eventRepository).findAll();
    }

    @Test
    void shouldDeleteEventById() {
        // GIVEN
        Long eventId = 1L;

        // WHEN
        eventService.delete(eventId);

        // THEN
        verify(eventRepository).deleteById(eventId);
    }

    @Test
    void shouldUpdateEvent() throws IOException {
        // GIVEN
        Event existingEvent = readJsonResource("/seed/event.json", Event.class);
        Event event = new Event(existingEvent.getId(), existingEvent.getTitle(), existingEvent.getImgUrl(), existingEvent.getBands(), existingEvent.getNbStars(), existingEvent.getComment());
        event.setNbStars(3);
        event.setComment("Good event");
        when(eventRepository.findById(event.getId())).thenReturn(Optional.of(existingEvent));

        // WHEN
        EventDto updatedEvent = eventService.update(event.getId(), event);

        // THEN
        assertThat(updatedEvent.getComment()).isEqualTo("Good event");
        assertThat(updatedEvent.getNbStars()).isEqualTo(3);
        verify(eventRepository, times(1)).save(existingEvent);
    }

    @Test
    void shouldThrowsEventNotFoundException() throws IOException {
        // GIVEN
        Long eventId = 2L;
        Event event = readJsonResource("/seed/event.json", Event.class);
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        // WHEN // THEN
        assertThatThrownBy(() -> eventService.update(eventId, event))
                .isInstanceOf(EventNotFoundException.class);
    }

    @Test
    void shouldReturnsFilteredEvents() throws IOException {
        // GIVEN
        Event event = readJsonResource("/seed/event.json", Event.class);
        Event event2 = readJsonResource("/seed/event2.json", Event.class);
        when(eventRepository.findAll()).thenReturn(List.of(event, event2));

        // WHEN
        List<EventDto> filteredEvents = eventService.getFilteredEvents("John");

        // THEN
        assertThat(filteredEvents).hasSize(1);
        assertThat(filteredEvents.get(0).getBands().iterator().next().getMembers())
                .extracting("name")
                .contains("John Doe");
    }

    @Test
    void shouldReturnsEmptyListWhenNoMatch() {
        // GIVEN
        Event event = new Event();
        Band band = new Band();
        Member member = new Member();
        member.setName("John");
        band.setMembers(Set.of(member));
        event.setBands(Set.of(band));
        when(eventRepository.findAll()).thenReturn(List.of(event));

        // WHEN
        List<EventDto> filteredEvents = eventService.getFilteredEvents("Jane");

        // THEN
        assertThat(filteredEvents).isEmpty();
    }
}