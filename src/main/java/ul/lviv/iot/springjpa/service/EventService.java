package ul.lviv.iot.springjpa.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ul.lviv.iot.springjpa.entity.Event;
import ul.lviv.iot.springjpa.exception.ResourceNotFoundException;
import ul.lviv.iot.springjpa.repository.EventRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService {


    private EventRepository eventRepository;

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    public Event findEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
        } else throw new ResourceNotFoundException("Not Found");
    }

    public Event updateEvent(Long id, Event event) {
        var toUpdate = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return eventRepository.save(event.setId(toUpdate.getId()));
    }

}
