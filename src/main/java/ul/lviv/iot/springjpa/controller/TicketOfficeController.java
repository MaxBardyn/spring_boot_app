package ul.lviv.iot.springjpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ul.lviv.iot.springjpa.entity.TicketOffice;
import ul.lviv.iot.springjpa.service.TicketOfficeService;

import java.util.List;

@RestController
@RequestMapping("offices")
@AllArgsConstructor
public class TicketOfficeController {


    private final TicketOfficeService ticketOfficeService;

    @GetMapping("/")
    public ResponseEntity<List<TicketOffice>> findAllTicketOffices() {
        var allOffices = ticketOfficeService.findAllTicketOffices();
        return (CollectionUtils.isEmpty(allOffices)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(allOffices, HttpStatus.OK));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TicketOffice> findTicketOfficeById(@PathVariable("id") Long id) {
        var office = ticketOfficeService.findTicketOfficeById(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(office, HttpStatus.OK));
    }

    @PostMapping("/")
    public ResponseEntity<TicketOffice> createTicketOffice(@RequestBody TicketOffice ticketOffice) {
        var newOffice = ticketOfficeService.createTicketOffice(ticketOffice);
        return newOffice == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(newOffice, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketOffice(final @PathVariable("id") Long id) {
        ticketOfficeService.deleteTicketOffice(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketOffice> updateEvent(final @PathVariable("id") Long id,
                                                    final @RequestBody TicketOffice ticketOffice) {
        var updatedOffice = ticketOfficeService.updateTicketOffice(id, ticketOffice);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(updatedOffice, HttpStatus.OK));

    }
}
