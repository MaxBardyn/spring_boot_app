package ul.lviv.iot.springjpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ul.lviv.iot.springjpa.dto.TicketDto;
import ul.lviv.iot.springjpa.mapper.TicketMapper;
import ul.lviv.iot.springjpa.service.TicketService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("tickets")
@AllArgsConstructor
public class TicketController {


    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @GetMapping("/")
    public ResponseEntity<List<TicketDto>> findAllTicketOffices() {
        var allTickets = ticketService.findAllTickets().stream()
                .map(ticketMapper::toDto).collect(Collectors.toList());
        return (CollectionUtils.isEmpty(allTickets)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(allTickets, HttpStatus.OK));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TicketDto> findTicketOfficeById(@PathVariable("id") Long id) {
        var ticket = ticketService.findTicketById(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(ticketMapper.toDto(ticket), HttpStatus.OK));
    }

    @PostMapping("/")
    public ResponseEntity<TicketDto> createTicketOffice(@RequestBody TicketDto ticket) {
        var newTicket = ticketService.createTicket(ticketMapper.toEntity(ticket));
        return newTicket == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(ticketMapper.toDto(newTicket), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketOffice(final @PathVariable("id") Long id) {
        ticketService.deleteTicket(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDto> updateEvent(final @PathVariable("id") Long id,
                                                 final @RequestBody TicketDto ticketDto) {
        var updatedTicket = ticketService.updateTicket(id, ticketMapper.toEntity(ticketDto));
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(ticketMapper.toDto(updatedTicket), HttpStatus.OK));

    }
}
