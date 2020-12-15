package ul.lviv.iot.springjpa.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ul.lviv.iot.springjpa.dto.TicketDto;
import ul.lviv.iot.springjpa.dto.TicketOfficeDto;
import ul.lviv.iot.springjpa.entity.Ticket;
import ul.lviv.iot.springjpa.entity.TicketOffice;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TicketOfficeMapper implements Mapper<TicketOffice, TicketOfficeDto> {


    private final TicketMapper ticketMapper;

    @Override
    public TicketOfficeDto toDto(TicketOffice ticketOffice) {
        return new TicketOfficeDto()
                .setId(ticketOffice.getId())
                .setTickets(ticketsToDto(ticketOffice.getTickets()));
    }

    @Override
    public TicketOffice toEntity(TicketOfficeDto ticketOfficeDto) {
        return new TicketOffice()
                .setId(ticketOfficeDto.getId())
                .setTickets(ticketsToEntity(ticketOfficeDto.getTickets()));
    }

    private List<TicketDto> ticketsToDto(List<Ticket> tickets) {
        return tickets.stream().map(ticketMapper::toDto).collect(Collectors.toList());
    }

    private List<Ticket> ticketsToEntity(List<TicketDto> tickets) {
        return tickets.stream().map(ticketMapper::toEntity).collect(Collectors.toList());
    }
}
