package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.repository.EventRepository;
import adeo.leroymerlin.cdp.dto.BandDto;
import adeo.leroymerlin.cdp.dto.EventDto;
import adeo.leroymerlin.cdp.dto.MemberDto;
import adeo.leroymerlin.cdp.entity.Band;
import adeo.leroymerlin.cdp.entity.Event;
import adeo.leroymerlin.cdp.entity.Member;
import adeo.leroymerlin.cdp.exception.EventNotFoundException;
import adeo.leroymerlin.cdp.mapper.BandMapper;
import adeo.leroymerlin.cdp.mapper.EventMapper;
import adeo.leroymerlin.cdp.mapper.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * Retrieve all events.
     *
     * @return a list of all events
     */
    public List<EventDto> getEvents() {
        return eventRepository.findAll().stream().map(EventMapper::toDto).toList();
    }

    /**
     * Delete an event by its ID.
     *
     * @param id the ID of the event to delete
     */
    @Transactional
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }


    /**
     * Update the number of stars and the comment of an event
     *
     * @param id the id of the event to update
     * @param event the new event data
     * @return the updated event
     */
    public EventDto update(Long id, Event event) {
        Event eventEntity = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException("Event not found with id: " + id));
        eventEntity.setNbStars(event.getNbStars());
        eventEntity.setComment(event.getComment());
        eventRepository.save(eventEntity);
        return EventMapper.toDto(eventEntity);
    }

    /**
     * 
     * Filter events by band member name and add :
     * <br>
     * - the number of matching bands in the filtered event's title
     * <br>
     * - the number of matching members' name in each band's name
     *
     * @param query the query to filter events
     * @return a list of filtered events
     */
    public List<EventDto> getFilteredEvents(String query) {
        List<Event> allEvents = eventRepository.findAll();
        List<EventDto> filteredEvents = new ArrayList<>();

        for (Event event : allEvents) {
            Set<BandDto> filteredBands = filterBands(event.getBands(), query);

            if (!filteredBands.isEmpty()) {
                EventDto eventDto = createEventDtoWithCountTitle(event, filteredBands);
                filteredEvents.add(eventDto);
            }
        }

        return filteredEvents;
    }

    /**
     * Filter bands by member name and add the number of matching members in the band's name
     *
     * @param bands the bands to filter
     * @param query the query to filter bands
     * @return a set of filtered bands
     */
    private Set<BandDto> filterBands(Set<Band> bands, String query) {
        Set<BandDto> filteredBands = new HashSet<>();

        for (Band band : bands) {
            Set<MemberDto> filteredMembers = filterMembers(band.getMembers(), query);

            if (!filteredMembers.isEmpty()) {
                BandDto bandDto = createBandDtoWithCountTitle(band, filteredMembers);
                filteredBands.add(bandDto);
            }
        }

        return filteredBands;
    }

    /**
     * Filter members by name
     *
     * @param members the members to filter
     * @param query the query to filter members
     * @return a set of filtered members
     */
    private Set<MemberDto> filterMembers(Set<Member> members, String query) {
        return members.stream()
                .filter(member -> member.getName().contains(query))
                .map(MemberMapper::toDto)
                .collect(Collectors.toSet());
    }

    /**
     * Create a band DTO from a band entity and a set of filtered members with a title containing the number of filtered members
     *
     * @param band the band to create the DTO from
     * @param filteredMembers the filtered members
     * @return the band DTO
     */
    private BandDto createBandDtoWithCountTitle(Band band, Set<MemberDto> filteredMembers) {
        BandDto bandDto = BandMapper.toDto(band);
        bandDto.setName(band.getName() + " [" + filteredMembers.size() + "]");
        bandDto.setMembers(filteredMembers);
        return bandDto;
    }

    /**
     * Create an event DTO from an event entity and a set of filtered bands with a title containing the number of filtered bands
     *
     * @param event the event to create the DTO from
     * @param filteredBands the filtered bands
     * @return the event DTO
     */
    private EventDto createEventDtoWithCountTitle(Event event, Set<BandDto> filteredBands) {
        EventDto eventDto = EventMapper.toDto(event);
        eventDto.setTitle(event.getTitle() + " [" + filteredBands.size() + "]");
        eventDto.setBands(filteredBands);
        return eventDto;
    }
}
