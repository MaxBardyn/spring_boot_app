package ul.lviv.iot.springjpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ul.lviv.iot.springjpa.entity.Artist;
import ul.lviv.iot.springjpa.service.ArtistService;

import java.util.List;

@Controller
@RequestMapping("artists")
@AllArgsConstructor
public class ArtistController {


    private ArtistService artistService;


    @GetMapping("/")
    public ResponseEntity<List<Artist>> findAllArtists() {
        var allArtists = artistService.findAllArtists();
        return (CollectionUtils.isEmpty(allArtists)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(allArtists, HttpStatus.OK));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Artist> findArtistById(@PathVariable("id") Long id) {
        var artist = artistService.findArtistById(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(artist, HttpStatus.OK));
    }

    @PostMapping("/")
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        var newArtist = artistService.createArtist(artist);
        return newArtist == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(newArtist, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(final @PathVariable("id") Long id) {
        artistService.deleteArtist(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(final @PathVariable("id") Long id,
                                               final @RequestBody Artist artist) {
        var updatedArtist = artistService.updateArtist(id, artist);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(updatedArtist, HttpStatus.OK));

    }
}
