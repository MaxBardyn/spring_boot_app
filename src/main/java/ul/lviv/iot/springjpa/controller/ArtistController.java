package ul.lviv.iot.springjpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ul.lviv.iot.springjpa.dto.ArtistDto;
import ul.lviv.iot.springjpa.mapper.ArtistMapper;
import ul.lviv.iot.springjpa.service.ArtistService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("artists")
@AllArgsConstructor
public class ArtistController {


    private ArtistService artistService;
    private ArtistMapper artistMapper;

    @GetMapping("/")
    public ResponseEntity<List<ArtistDto>> findAllArtists() {
        var allArtists = artistService.findAllArtists().stream()
                .map(artistMapper::toDto).collect(Collectors.toList());
        return (CollectionUtils.isEmpty(allArtists)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(allArtists, HttpStatus.OK));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ArtistDto> findArtistById(@PathVariable("id") Long id) {
        var artist = artistService.findArtistById(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(artistMapper.toDto(artist), HttpStatus.OK));
    }

    @PostMapping("/")
    public ResponseEntity<ArtistDto> createArtist(@RequestBody ArtistDto artist) {
        var newArtist = artistService.createArtist(artistMapper.toEntity(artist));
        return newArtist == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(artistMapper.toDto(newArtist), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(final @PathVariable("id") Long id) {
        artistService.deleteArtist(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistDto> updateArtist(final @PathVariable("id") Long id,
                                                  final @RequestBody ArtistDto artist) {
        var updatedArtist = artistService.updateArtist(id, artistMapper.toEntity(artist));
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(artistMapper.toDto(updatedArtist), HttpStatus.OK));

    }
}
