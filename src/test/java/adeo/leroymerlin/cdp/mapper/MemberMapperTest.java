package adeo.leroymerlin.cdp.mapper;

import adeo.leroymerlin.cdp.dto.MemberDto;
import adeo.leroymerlin.cdp.entity.Member;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static adeo.leroymerlin.cdp.TestUtils.readJsonResource;
import static org.assertj.core.api.Assertions.assertThat;

public class MemberMapperTest {

    @Test
    public void testToDto() throws IOException {
        //GIVEN
        Member member = readJsonResource("/seed/member.json", Member.class);
        MemberDto expectedMemberDto = readJsonResource("expected/member.json", MemberDto.class);
        //WHEN
        MemberDto memberDto = MemberMapper.toDto(member);
        //THEN
        assertThat(memberDto).isEqualTo(expectedMemberDto);
    }

    @Test
    public void testToEntity() throws IOException {
        //GIVEN
        MemberDto memberDto = readJsonResource("/seed/member.json", MemberDto.class);
        Member expectedMember = readJsonResource("expected/member.json", Member.class);
        //WHEN
        Member member = MemberMapper.toEntity(memberDto);
        //THEN
        assertThat(member).isEqualTo(expectedMember);
    }
}