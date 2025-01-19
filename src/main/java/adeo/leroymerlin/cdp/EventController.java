package adeo.leroymerlin.cdp;

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

    @GetMapping(value = "/")
    public ResponseEntity<List<EventDto>>  findEvents() {
        return ResponseEntity.ok(eventService.getEvents());
    }

    @GetMapping(value = "/search/{query}")
    public ResponseEntity<List<EventDto>> findEvents(@PathVariable String query) {
        return ResponseEntity.ok(eventService.getFilteredEvents(query));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return ResponseEntity.ok(eventService.update(id, event));
    }
}
