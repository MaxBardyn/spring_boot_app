package ul.lviv.iot.springjpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ul.lviv.iot.springjpa.entity.Location;
import ul.lviv.iot.springjpa.service.LocationService;

import java.util.List;

@RestController
@RequestMapping("locations")
@AllArgsConstructor
public class LocationController {


    private LocationService locationService;

    @GetMapping("/")
    public ResponseEntity<List<Location>> findAllLocations() {
        var allLocations = locationService.findAllLocations();
        return (CollectionUtils.isEmpty(allLocations)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(allLocations, HttpStatus.OK));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Location> findLocationById(@PathVariable("id") Long id) {
        var locationById = locationService.findLocationById(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(locationById, HttpStatus.OK));
    }

    @PostMapping("/")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        var newLocation = locationService.createLocation(location);
        return newLocation == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(newLocation, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(final @PathVariable("id") Long id) {
        locationService.deleteLocation(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(final @PathVariable("id") Long id,
                                                 final @RequestBody Location location) {
        var updatedLocation = locationService.updateLocation(id, location);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(updatedLocation, HttpStatus.OK));
    }
}
