package ul.lviv.iot.springjpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ul.lviv.iot.springjpa.entity.Ticket;
import ul.lviv.iot.springjpa.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("tickets")
@AllArgsConstructor
public class TicketController {


    private final TicketService ticketService;

    @GetMapping("/")
    public ResponseEntity<List<Ticket>> findAllTicketOffices() {
        var allOffices = ticketService.findAllTickets();
        return (CollectionUtils.isEmpty(allOffices)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(allOffices, HttpStatus.OK));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Ticket> findTicketOfficeById(@PathVariable("id") Long id) {
        var office = ticketService.findTicketById(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(office, HttpStatus.OK));
    }

    @PostMapping("/")
    public ResponseEntity<Ticket> createTicketOffice(@RequestBody Ticket ticket) {
        var newOffice = ticketService.createTicket(ticket);
        return newOffice == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(newOffice, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketOffice(final @PathVariable("id") Long id) {
        ticketService.deleteTicket(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateEvent(final @PathVariable("id") Long id,
                                              final @RequestBody Ticket ticketOffice) {
        var updatedOffice = ticketService.updateTicket(id, ticketOffice);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(updatedOffice, HttpStatus.OK));

    }
}
