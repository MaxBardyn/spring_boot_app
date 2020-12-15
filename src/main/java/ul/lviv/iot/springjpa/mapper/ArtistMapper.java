package ul.lviv.iot.springjpa.mapper;

import org.springframework.stereotype.Component;
import ul.lviv.iot.springjpa.dto.ArtistDto;
import ul.lviv.iot.springjpa.entity.Artist;

@Component
public class ArtistMapper implements Mapper<Artist, ArtistDto> {


    @Override
    public ArtistDto toDto(Artist artist) {
        return new ArtistDto()
                .setId(artist.getId())
                .setName(artist.getName())
                .setSurname(artist.getSurname())
                .setAge(artist.getAge())
                .setAlias(artist.getAlias());
    }

    @Override
    public Artist toEntity(ArtistDto artistDto) {
        return new Artist()
                .setId(artistDto.getId())
                .setName(artistDto.getName())
                .setSurname(artistDto.getSurname())
                .setAge(artistDto.getAge())
                .setAlias(artistDto.getAlias());
    }
}
