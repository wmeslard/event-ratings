package adeo.leroymerlin.cdp;

import adeo.leroymerlin.cdp.exceptions.EventNotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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
    public List<Event> getEvents() {
        return eventRepository.findAll();
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
    public List<Event> getFilteredEvents(String query) {
        List<Event> allEvents = eventRepository.findAll();
        List<Event> filteredEvents = new ArrayList<>();

        for (Event event : allEvents) {
            Set<Band> filteredBands = new HashSet<>();

            for (Band band : event.getBands()) {
                Set<Member> filteredMembers = new HashSet<>();

                for (Member member : band.getMembers()) {
                    if (member.getName().contains(query)) {
                        filteredMembers.add(member);
                    }
                }

                if (!filteredMembers.isEmpty()) {
                    Band newBand = new Band();
                    newBand.setName(band.getName() + " [" + filteredMembers.size() + "]");
                    newBand.setMembers(filteredMembers);
                    filteredBands.add(newBand);
                }
            }

            if (!filteredBands.isEmpty()) {
                Event newEvent = new Event();
                newEvent.setTitle(event.getTitle() + " [" + filteredBands.size() + "]");
                newEvent.setBands(filteredBands);
                filteredEvents.add(newEvent);
            }
        }

        return filteredEvents;
    }

    /**
     * Update the number of stars and the comment of an event
     *
     * @param id the id of the event to update
     * @param event the new event data
     * @return the updated event
     */
    public Event update(Long id, Event event) {
        Event eventEntity = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException("blabla bla not found"));
        eventEntity.setNbStars(event.getNbStars());
        eventEntity.setComment(event.getComment());
        eventRepository.save(eventEntity);
        return eventEntity;
    }
}
