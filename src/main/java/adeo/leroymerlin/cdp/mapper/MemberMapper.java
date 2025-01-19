package adeo.leroymerlin.cdp.mapper;

import adeo.leroymerlin.cdp.dto.EventDto;
import adeo.leroymerlin.cdp.dto.MemberDto;
import adeo.leroymerlin.cdp.entity.Event;
import adeo.leroymerlin.cdp.entity.Member;

public class BandMapper
{

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
