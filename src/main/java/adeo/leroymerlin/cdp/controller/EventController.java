package adeo.leroymerlin.cdp.controller;

import adeo.leroymerlin.cdp.service.EventService;
import adeo.leroymerlin.cdp.dto.EventDto;
import adeo.leroymerlin.cdp.entity.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * GET /api/events/ : Get all events.
     *
     * @return a ResponseEntity containing a list of EventDto objects and HTTP status 200 (OK)
     */
    @GetMapping(value = "/")
    public ResponseEntity<List<EventDto>>  findEvents() {
        return ResponseEntity.ok(eventService.getEvents());
    }

    /**
     * GET /api/events/search/{query} : Get filtered events by query.
     *
     * @param query the search query to filter events
     * @return a ResponseEntity containing a list of filtered EventDto objects and HTTP status 200 (OK)
     */
    @GetMapping(value = "/search/{query}")
    public ResponseEntity<List<EventDto>> findEvents(@PathVariable String query) {
        return ResponseEntity.ok(eventService.getFilteredEvents(query));
    }

    /**
     * DELETE /api/events/{id} : Delete an event by ID.
     *
     * @param id the ID of the event to delete
     * @return a ResponseEntity with HTTP status 200 (OK)
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * PATCH /api/events/{id} : Update an event by ID.
     *
     * @param id the ID of the event to update
     * @param event the event object containing updated data
     * @return a ResponseEntity containing the updated EventDto object and HTTP status 200 (OK)
     */
    @PatchMapping(value = "/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return ResponseEntity.ok(eventService.update(id, event));
    }
}
