package ul.lviv.iot.springjpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ul.lviv.iot.springjpa.dto.EventDto;
import ul.lviv.iot.springjpa.mapper.EventMapper;
import ul.lviv.iot.springjpa.service.EventService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("events")
@AllArgsConstructor
public class EventController {


    private final EventService eventService;
    private final EventMapper eventMapper;

    @GetMapping("/")
    public ResponseEntity<List<EventDto>> findAllEvents() {
        var allEvents = eventService.findAllEvents().stream()
                .map(eventMapper::toDto).collect(Collectors.toList());
        return (CollectionUtils.isEmpty(allEvents)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(allEvents, HttpStatus.OK));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EventDto> findEventById(@PathVariable("id") Long id) {
        var event = eventService.findEventById(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(eventMapper.toDto(event), HttpStatus.OK));
    }

    @PostMapping("/")
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto event) {
        var newEvent = eventService.createEvent(eventMapper.toEntity(event));
        return newEvent == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(eventMapper.toDto(newEvent), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(final @PathVariable("id") Long id) {
        eventService.deleteEvent(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(final @PathVariable("id") Long id,
                                                final @RequestBody EventDto event) {
        var updatedEvent = eventService.updateEvent(id, eventMapper.toEntity(event));
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(eventMapper.toDto(updatedEvent), HttpStatus.OK));

    }
}
