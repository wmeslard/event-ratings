package adeo.leroymerlin.cdp.dto;

import java.util.Objects;

public class MemberDto {

    private Long id;
    String name;

    public MemberDto() {}

    public MemberDto(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDto memberDto = (MemberDto) o;
        return Objects.equals(id, memberDto.id) && Objects.equals(name, memberDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
