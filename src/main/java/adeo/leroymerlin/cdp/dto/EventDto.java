package adeo.leroymerlin.cdp.dto;

import adeo.leroymerlin.cdp.entity.Band;

import java.util.Objects;
import java.util.Set;

public class EventDto {

    private Long id;
    private String title;
    private String imgUrl;
    private Set<BandDto> bands;
    private Integer nbStars;
    private String comment;

    public EventDto() {}

    public EventDto(Long id, String title, String imgUrl, Set<BandDto> bands, Integer nbStars, String comment) {
        this.id = id;
        this.title = title;
        this.imgUrl = imgUrl;
        this.bands = bands;
        this.nbStars = nbStars;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<BandDto> getBands() {
        return bands;
    }

    public void setBands(Set<BandDto> bands) {
        this.bands = bands;
    }

    public Integer getNbStars() {
        return nbStars;
    }

    public void setNbStars(Integer nbStars) {
        this.nbStars = nbStars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDto eventDto = (EventDto) o;
        return Objects.equals(id, eventDto.id) && Objects.equals(title, eventDto.title) && Objects.equals(imgUrl, eventDto.imgUrl) && Objects.equals(bands, eventDto.bands) && Objects.equals(nbStars, eventDto.nbStars) && Objects.equals(comment, eventDto.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, imgUrl, bands, nbStars, comment);
    }
}
