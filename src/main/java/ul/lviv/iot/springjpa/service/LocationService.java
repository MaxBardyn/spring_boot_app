package ul.lviv.iot.springjpa.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ul.lviv.iot.springjpa.entity.Location;
import ul.lviv.iot.springjpa.exception.ResourceNotFoundException;
import ul.lviv.iot.springjpa.repository.LocationRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {


    private LocationRepository locationRepository;

    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    public Location findLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public void deleteLocation(Long id) {
        if (locationRepository.existsById(id)) {
            locationRepository.deleteById(id);
        } else throw new ResourceNotFoundException("Not Found");
    }

    public Location updateLocation(Long id, Location location) {
        var toUpdate = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return locationRepository.save(location.setId(toUpdate.getId()));
    }
}
