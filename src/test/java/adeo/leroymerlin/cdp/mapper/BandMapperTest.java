package adeo.leroymerlin.cdp.mapper;

import adeo.leroymerlin.cdp.dto.BandDto;
import adeo.leroymerlin.cdp.entity.Band;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static adeo.leroymerlin.cdp.TestUtils.readJsonResource;
import static org.assertj.core.api.Assertions.assertThat;

public class BandMapperTest {

    @Test
    public void testToDto() throws IOException {
        //GIVEN
        Band band = readJsonResource("/seed/band.json", Band.class);
        BandDto expectedBandDto = readJsonResource("expected/band.json", BandDto.class);
        //WHEN
        BandDto bandDto = BandMapper.toDto(band);
        //THEN
        assertThat(bandDto).isEqualTo(expectedBandDto);
    }

    @Test
    public void testToEntity() throws IOException {
        //GIVEN
        BandDto bandDto = readJsonResource("/seed/band.json", BandDto.class);
        Band expectedBand = readJsonResource("expected/band.json", Band.class);
        //WHEN
        Band band = BandMapper.toEntity(bandDto);
        //THEN
        assertThat(band).isEqualTo(expectedBand);
    }
}