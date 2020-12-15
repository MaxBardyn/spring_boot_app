package ul.lviv.iot.springjpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ul.lviv.iot.springjpa.dto.TicketOfficeDto;
import ul.lviv.iot.springjpa.mapper.TicketOfficeMapper;
import ul.lviv.iot.springjpa.service.TicketOfficeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("offices")
@AllArgsConstructor
public class TicketOfficeController {


    private final TicketOfficeService ticketOfficeService;
    private final TicketOfficeMapper ticketOfficeMapper;

    @GetMapping("/")
    public ResponseEntity<List<TicketOfficeDto>> findAllTicketOffices() {
        var allOffices = ticketOfficeService.findAllTicketOffices().stream()
                .map(ticketOfficeMapper::toDto).collect(Collectors.toList());
        return (CollectionUtils.isEmpty(allOffices)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(allOffices, HttpStatus.OK));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TicketOfficeDto> findTicketOfficeById(@PathVariable("id") Long id) {
        var office = ticketOfficeService.findTicketOfficeById(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(ticketOfficeMapper.toDto(office), HttpStatus.OK));
    }

    @PostMapping("/")
    public ResponseEntity<TicketOfficeDto> createTicketOffice(@RequestBody TicketOfficeDto ticketOffice) {
        var newOffice = ticketOfficeService.createTicketOffice(ticketOfficeMapper.toEntity(ticketOffice));
        return newOffice == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(ticketOfficeMapper.toDto(newOffice), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketOffice(final @PathVariable("id") Long id) {
        ticketOfficeService.deleteTicketOffice(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketOfficeDto> updateEvent(final @PathVariable("id") Long id,
                                                       final @RequestBody TicketOfficeDto ticketOffice) {
        var updatedOffice = ticketOfficeService.updateTicketOffice(id, ticketOfficeMapper.toEntity(ticketOffice));
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(ticketOfficeMapper.toDto(updatedOffice), HttpStatus.OK));

    }
}
