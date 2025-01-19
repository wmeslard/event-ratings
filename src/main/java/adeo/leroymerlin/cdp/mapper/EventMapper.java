package adeo.leroymerlin.cdp;

import adeo.leroymerlin.cdp.dto.EventDto;
import adeo.leroymerlin.cdp.dto.MemberDto;
import adeo.leroymerlin.cdp.entity.Event;
import adeo.leroymerlin.cdp.entity.Member;

public class EventMapper
{
    public static EventDto toDto(Event event) {
        return new EventDto(event.getId(), event.getTitle(), event.getImgUrl(), event.getBands(), event.getNbStars(), event.getComment());
    }

    public static Event toEntity(EventDto eventDto) {
        Event event = new Event();
        event.setId(eventDto.getId());
        event.setTitle(eventDto.getTitle());
        event.setImgUrl(eventDto.getImgUrl());
        event.setBands(eventDto.getBands());
        event.setNbStars(eventDto.getNbStars());
        event.setComment(eventDto.getComment());
        return event;
    }

    public static MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getName());
    }

    public static Member toEntity(MemberDto memberDto) {
        Member member = new Member();
        member.setId(memberDto.getId());
        member.setName(memberDto.getName());
        return member;
    }
}
