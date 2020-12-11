package ul.lviv.iot.springjpa.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ul.lviv.iot.springjpa.entity.Ticket;
import ul.lviv.iot.springjpa.exception.ResourceNotFoundException;
import ul.lviv.iot.springjpa.repository.TicketRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {


    private TicketRepository ticketRepository;

    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket findTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    public Ticket createTicket(Ticket ticketOffice) {
        return ticketRepository.save(ticketOffice);
    }

    public void deleteTicket(Long id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
        } else throw new ResourceNotFoundException("Not Found");
    }

    public Ticket updateTicket(Long id, Ticket ticketOffice) {
        var toUpdate = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return ticketRepository.save(ticketOffice.setId(toUpdate.getId()));
    }
}
