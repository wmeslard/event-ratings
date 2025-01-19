package adeo.leroymerlin.cdp.mapper;

import adeo.leroymerlin.cdp.dto.EventDto;
import adeo.leroymerlin.cdp.entity.Event;
import adeo.leroymerlin.cdp.entity.Band;
import adeo.leroymerlin.cdp.dto.BandDto;
import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventMapperTest {

    @Test
    public void testToDto() {
        // Arrange
        Set<Band> bands = new HashSet<>();
        bands.add(new Band(1L, "Band 1", new HashSet<>()));
        Event event = new Event(1L, "Event 1", "imgUrl", bands, 5, "Great event");

        // Act
        EventDto eventDto = EventMapper.toDto(event);

        // Assert
        assertEquals(event.getId(), eventDto.getId());
        assertEquals(event.getTitle(), eventDto.getTitle());
        assertEquals(event.getImgUrl(), eventDto.getImgUrl());
        assertEquals(event.getNbStars(), eventDto.getNbStars());
        assertEquals(event.getComment(), eventDto.getComment());
        assertEquals(event.getBands().size(), eventDto.getBands().size());
    }

    @Test
    public void testToEntity() {
        // Arrange
        Set<BandDto> bandDtos = new HashSet<>();
        bandDtos.add(new BandDto(1L, "Band 1", new HashSet<>()));
        EventDto eventDto = new EventDto(1L, "Event 1", "imgUrl", bandDtos, 5, "Great event");

        // Act
        Event event = EventMapper.toEntity(eventDto);

        // Assert
        assertEquals(eventDto.getId(), event.getId());
        assertEquals(eventDto.getTitle(), event.getTitle());
        assertEquals(eventDto.getImgUrl(), event.getImgUrl());
        assertEquals(eventDto.getNbStars(), event.getNbStars());
        assertEquals(eventDto.getComment(), event.getComment());
        assertEquals(eventDto.getBands().size(), event.getBands().size());
    }
}