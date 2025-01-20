package adeo.leroymerlin.cdp.controller;

import adeo.leroymerlin.cdp.dto.EventDto;
import adeo.leroymerlin.cdp.entity.Event;
import adeo.leroymerlin.cdp.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import java.util.List;

import static adeo.leroymerlin.cdp.TestUtils.readJsonResource;
import static org.mockito.Mockito.when;


@ComponentScan(basePackages = "adeo.leroymerlin.cdp.controller")
@WebMvcTest(value = EventController.class)
class EventControllerTest {

    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private EventService eventService;

    EventControllerTest(WebApplicationContext webApplicationContext) {this.webApplicationContext = webApplicationContext;}

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test void shouldPerformGetListOfEvents() throws Exception {
        // GIVEN
        EventDto eventDto = readJsonResource("/seed/event.json", EventDto.class);
        List<EventDto> events = List.of(eventDto);
        when(eventService.getEvents()).thenReturn(events);

        // WHEN
        //THEN
        mockMvc.perform(get("/api/events/"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(events)));
    }

    @Test
    void shouldReturnFilteredEvents() throws Exception {
        // GIVEN
        EventDto eventDto = readJsonResource("/seed/event2.json", EventDto.class);
        List<EventDto> events = List.of(eventDto);
        when(eventService.getFilteredEvents("John")).thenReturn(events);

        // WHEN
        // THEN
        mockMvc.perform(get("/api/events/search/John"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(events)));
    }

    @Test
    void shouldDeleteEvent() throws Exception {
        // WHEN
        // THEN
        mockMvc.perform(delete("/api/events/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateEvent() throws Exception {
        // GIVEN
        Event event = readJsonResource("/seed/event.json", Event.class);
        EventDto eventDto = readJsonResource("/seed/event2.json", EventDto.class);
        when(eventService.update(1L, event)).thenReturn(eventDto);

        // WHEN
        // THEN
        mockMvc.perform(patch("/api/events/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(event)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(eventDto)));
    }
}