package adeo.leroymerlin.cdp;

import adeo.leroymerlin.cdp.dto.EventDto;
import adeo.leroymerlin.cdp.entity.Event;
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
    public List<EventDto> findEvents() {
        return eventService.getEvents();
    }

    @GetMapping(value = "/search/{query}")
    public List<EventDto> findEvents(@PathVariable String query) {
        return eventService.getFilteredEvents(query);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
    }

    @PatchMapping(value = "/{id}")
    public EventDto updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return eventService.update(id, event);
    }
}
