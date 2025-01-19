package adeo.leroymerlin.cdp.dto;

import java.util.Objects;
import java.util.Set;

public class BandDto {

    private Long id;
    private String name;
    private Set<MemberDto> members;

    public BandDto() {}

    public BandDto(Long id, String name, Set<MemberDto> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MemberDto> getMembers() {
        return members;
    }

    public void setMembers(Set<MemberDto> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BandDto bandDto = (BandDto) o;
        return Objects.equals(id, bandDto.id) && Objects.equals(name, bandDto.name) && Objects.equals(members, bandDto.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, members);
    }
}
