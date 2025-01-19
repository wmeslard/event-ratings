package adeo.leroymerlin.cdp.mapper;

import adeo.leroymerlin.cdp.dto.BandDto;
import adeo.leroymerlin.cdp.entity.Band;

public class BandMapper {

    public static BandDto toDto(Band band) {
        return new BandDto(band.getId(), band.getName(), band.getMembers());
    }

    public static Band toEntity(BandDto bandDto) {
        Band band = new Band();
        band.setId(bandDto.getId());
        band.setName(bandDto.getName());
        band.setMembers(bandDto.getMembers());
        return band;
    }

}
