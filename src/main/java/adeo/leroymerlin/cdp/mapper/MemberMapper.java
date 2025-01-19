package adeo.leroymerlin.cdp.mapper;

import adeo.leroymerlin.cdp.dto.MemberDto;
import adeo.leroymerlin.cdp.entity.Member;

public class MemberMapper
{

    public static MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getName());
    }

    public static Member toEntity(MemberDto memberDto) {
        return new Member(memberDto.getId(), memberDto.getName());
    }
}
