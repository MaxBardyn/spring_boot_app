package ul.lviv.iot.springjpa.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ul.lviv.iot.springjpa.entity.TicketOffice;
import ul.lviv.iot.springjpa.exception.ResourceNotFoundException;
import ul.lviv.iot.springjpa.repository.TicketOfficeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketOfficeService {


    private TicketOfficeRepository ticketOfficeRepository;

    public List<TicketOffice> findAllTicketOffices() {
        return ticketOfficeRepository.findAll();
    }

    public TicketOffice findTicketOfficeById(Long id) {
        return ticketOfficeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    public TicketOffice createTicketOffice(TicketOffice ticketOffice) {
        return ticketOfficeRepository.save(ticketOffice);
    }

    public void deleteTicketOffice(Long id) {
        if (ticketOfficeRepository.existsById(id)) {
            ticketOfficeRepository.deleteById(id);
        } else throw new ResourceNotFoundException("Not Found");
    }

    public TicketOffice updateTicketOffice(Long id, TicketOffice ticketOffice) {
        var toUpdate = ticketOfficeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return ticketOfficeRepository.save(ticketOffice.setId(toUpdate.getId()));
    }
}
