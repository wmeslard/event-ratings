package adeo.leroymerlin.cdp;

import adeo.leroymerlin.cdp.dto.EventDto;
import adeo.leroymerlin.cdp.entity.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

import static adeo.leroymerlin.cdp.TestUtils.readJsonResource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {

    @InjectMocks
    private EventController eventController;

    @Mock
    private EventService eventService;

    @Test
    void shouldReturnListOfEvents() throws IOException {
        // GIVEN
        EventDto eventDto = readJsonResource("/seed/event.json", EventDto.class);
        List<EventDto> events = List.of(eventDto);
        when(eventService.getEvents()).thenReturn(events);

        // WHEN
        ResponseEntity<List<EventDto>> responseEntity = eventController.findEvents();

        // THEN
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(events);
    }

    @Test
    void shouldReturnFilteredEvents() throws IOException {
        // GIVEN
        EventDto eventDto = readJsonResource("/seed/event.json", EventDto.class);
        List<EventDto> events = List.of(eventDto);
        when(eventService.getFilteredEvents("test")).thenReturn(events);

        // WHEN
        ResponseEntity<List<EventDto>> responseEntity = eventController.findEvents("test");

        // THEN
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(events);
    }

    @Test
    void shouldDeleteEvent() {
        // WHEN
        ResponseEntity<Void> responseEntity = eventController.deleteEvent(1L);

        // THEN
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldUpdateEvent() throws IOException {
        // GIVEN
        EventDto eventDto = readJsonResource("/seed/event.json", EventDto.class);
        Event event = readJsonResource("/seed/event.json", Event.class);
        when(eventService.update(1L, event)).thenReturn(eventDto);

        // WHEN
        ResponseEntity<EventDto> responseEntity = eventController.updateEvent(1L, event);

        // THEN
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(eventDto);
    }
}