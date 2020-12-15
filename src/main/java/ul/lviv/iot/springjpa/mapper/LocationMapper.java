package ul.lviv.iot.springjpa.mapper;

import org.springframework.stereotype.Component;
import ul.lviv.iot.springjpa.dto.LocationDto;
import ul.lviv.iot.springjpa.entity.Location;

@Component
public class LocationMapper implements Mapper<Location, LocationDto> {



    @Override
    public LocationDto toDto(Location location) {
        return new LocationDto()
                .setId(location.getId())
                .setName(location.getName())
                .setAddress(location.getAddress());
    }

    @Override
    public Location toEntity(LocationDto locationDto) {
        return new Location()
                .setId(locationDto.getId())
                .setName(locationDto.getName())
                .setAddress(locationDto.getAddress());
    }
}
