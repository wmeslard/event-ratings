package adeo.leroymerlin.cdp.mapper;

import adeo.leroymerlin.cdp.dto.EventDto;
import adeo.leroymerlin.cdp.entity.Event;

import java.util.stream.Collectors;

public class EventMapper
{
    public static EventDto toDto(Event event) {
        return new EventDto(
                event.getId(),
                event.getTitle(),
                event.getImgUrl(),
                event.getBands().stream().map(BandMapper::toDto).collect(Collectors.toSet()),
                event.getNbStars(),
                event.getComment());
    }


    public static Event toEntity(EventDto eventDto) {
        return new Event(eventDto.getId(),
                eventDto.getTitle(),
                eventDto.getImgUrl(),
                eventDto.getBands().stream().map(BandMapper::toEntity).collect(Collectors.toSet()),
                eventDto.getNbStars(),
                eventDto.getComment());
    }

}
