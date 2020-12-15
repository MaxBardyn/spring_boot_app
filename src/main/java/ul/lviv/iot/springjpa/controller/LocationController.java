package ul.lviv.iot.springjpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ul.lviv.iot.springjpa.dto.LocationDto;
import ul.lviv.iot.springjpa.mapper.LocationMapper;
import ul.lviv.iot.springjpa.service.LocationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("locations")
@AllArgsConstructor
public class LocationController {


    private final LocationService locationService;
    private final LocationMapper locationMapper;


    @GetMapping("/")
    public ResponseEntity<List<LocationDto>> findAllLocations() {
        var allLocations = locationService.findAllLocations().stream()
                .map(locationMapper::toDto).collect(Collectors.toList());
        return (CollectionUtils.isEmpty(allLocations)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(allLocations, HttpStatus.OK));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<LocationDto> findLocationById(@PathVariable("id") Long id) {
        var location = locationService.findLocationById(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(locationMapper.toDto(location), HttpStatus.OK));
    }

    @PostMapping("/")
    public ResponseEntity<LocationDto> createLocation(@RequestBody LocationDto location) {
        var newLocation = locationService.createLocation(locationMapper.toEntity(location));
        return newLocation == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(locationMapper.toDto(newLocation), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(final @PathVariable("id") Long id) {
        locationService.deleteLocation(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDto> updateLocation(final @PathVariable("id") Long id,
                                                      final @RequestBody LocationDto location) {
        var updatedLocation = locationService.updateLocation(id, locationMapper.toEntity(location));
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(locationMapper.toDto(updatedLocation), HttpStatus.OK));
    }
}
