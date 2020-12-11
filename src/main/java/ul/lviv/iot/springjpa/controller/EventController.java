package ul.lviv.iot.springjpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ul.lviv.iot.springjpa.entity.Event;
import ul.lviv.iot.springjpa.service.EventService;

import java.util.List;

@RestController
@RequestMapping("events")
@AllArgsConstructor
public class EventController {


    private final EventService eventService;

    @GetMapping("/")
    public ResponseEntity<List<Event>> findAllEvents() {
        var allEvents = eventService.findAllEvents();
        return (CollectionUtils.isEmpty(allEvents)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(allEvents, HttpStatus.OK));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Event> findEventById(@PathVariable("id") Long id) {
        var event = eventService.findEventById(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(event, HttpStatus.OK));
    }

    @PostMapping("/")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        var newEvent = eventService.createEvent(event);
        return newEvent == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(final @PathVariable("id") Long id) {
        eventService.deleteEvent(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(final @PathVariable("id") Long id,
                                             final @RequestBody Event event) {
        var updatedEvent = eventService.updateEvent(id, event);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(updatedEvent, HttpStatus.OK));

    }
}
