package adeo.leroymerlin.cdp.dto;

import java.util.Set;

public class BandDto {

    private Long id;
    private String name;
    private Set<MemberDto> members;

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
}
