package ul.lviv.iot.springjpa.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ul.lviv.iot.springjpa.entity.Artist;
import ul.lviv.iot.springjpa.exception.ResourceNotFoundException;
import ul.lviv.iot.springjpa.repository.ArtistRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ArtistService {


    private ArtistRepository artistRepository;

    public List<Artist> findAllArtists() {
        return artistRepository.findAll();
    }

    public Artist findArtistById(Long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public void deleteArtist(Long id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
        } else throw new ResourceNotFoundException("Not Found");
    }

    public Artist updateArtist(Long id, Artist artist) {
        var toUpdate = artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return artistRepository.save(artist.setId(toUpdate.getId()));
    }
}
