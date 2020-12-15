package ul.lviv.iot.springjpa.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ul.lviv.iot.springjpa.dto.ArtistDto;
import ul.lviv.iot.springjpa.dto.EventDto;
import ul.lviv.iot.springjpa.entity.Artist;
import ul.lviv.iot.springjpa.entity.Event;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EventMapper implements Mapper<Event, EventDto> {


    private final ArtistMapper artistMapper;
    private final LocationMapper locationMapper;

    @Override
    public EventDto toDto(Event event) {
        return new EventDto()
                .setId(event.getId())
                .setName(event.getName())
                .setArtist(artistsToDto(event.getArtists()))
                .setLocation(locationMapper.toDto(event.getLocation()))
                .setArtist(artistsToDto(event.getArtists()));
    }

    @Override
    public Event toEntity(EventDto eventDto) {
        return new Event()
                .setId(eventDto.getId())
                .setName(eventDto.getName())
                .setArtists(artistsToEntity(eventDto.getArtist()))
                .setLocation(locationMapper.toEntity(eventDto.getLocation()))
                .setArtists(artistsToEntity(eventDto.getArtist()));
    }

    private List<ArtistDto> artistsToDto(List<Artist> artists) {
        return artists.stream().map(artistMapper::toDto).collect(Collectors.toList());
    }

    private List<Artist> artistsToEntity(List<ArtistDto> artists) {
        return artists.stream().map(artistMapper::toEntity).collect(Collectors.toList());
    }
}
