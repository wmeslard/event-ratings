package adeo.leroymerlin.cdp.mapper;

import adeo.leroymerlin.cdp.dto.BandDto;
import adeo.leroymerlin.cdp.entity.Band;

import java.util.stream.Collectors;

public class BandMapper {

    public static BandDto toDto(Band band) {
        return new BandDto(band.getId(), band.getName(), band.getMembers().stream().map(MemberMapper::toDto).collect(Collectors.toSet()));
    }

    public static Band toEntity(BandDto bandDto) {
        return new Band(bandDto.getId(), bandDto.getName(), bandDto.getMembers().stream().map(MemberMapper::toEntity).collect(Collectors.toSet()));
    }

}
