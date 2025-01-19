package adeo.leroymerlin.cdp.mapper;

import adeo.leroymerlin.cdp.dto.EventDto;
import adeo.leroymerlin.cdp.entity.Event;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static adeo.leroymerlin.cdp.TestUtils.readJsonResource;
import static org.assertj.core.api.Assertions.assertThat;

public class EventMapperTest {

    @Test
    public void testToDto() throws IOException {
        //GIVEN
        Event event = readJsonResource("/seed/event.json", Event.class);
        EventDto expectedEventDto = readJsonResource("expected/event.json", EventDto.class);
        //WHEN
        EventDto eventDto = EventMapper.toDto(event);
        //THEN
        assertThat(eventDto).isEqualTo(expectedEventDto);
    }

    @Test
    public void testToEntity() throws IOException {
        //GIVEN
        EventDto eventDto = readJsonResource("/seed/event.json", EventDto.class);
        Event expectedEvent = readJsonResource("expected/event.json", Event.class);
        //WHEN
        Event event = EventMapper.toEntity(eventDto);
        //THEN
        assertThat(event).isEqualTo(expectedEvent);
    }
}