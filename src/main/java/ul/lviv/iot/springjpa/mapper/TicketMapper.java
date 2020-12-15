package ul.lviv.iot.springjpa.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ul.lviv.iot.springjpa.dto.TicketDto;
import ul.lviv.iot.springjpa.entity.Ticket;

@Component
@AllArgsConstructor
public class TicketMapper implements Mapper<Ticket, TicketDto> {


    private final EventMapper eventMapper;

    @Override
    public TicketDto toDto(Ticket ticket) {
        return new TicketDto()
                .setId(ticket.getId())
                .setPrice(ticket.getPrice())
                .setSector(ticket.getSector())
                .setPlaceNumber(ticket.getPlaceNumber())
                .setEvent(eventMapper.toDto(ticket.getEvent()));
    }

    @Override
    public Ticket toEntity(TicketDto ticketDto) {
        return new Ticket()
                .setId(ticketDto.getId())
                .setPrice(ticketDto.getPrice())
                .setSector(ticketDto.getSector())
                .setPlaceNumber(ticketDto.getPlaceNumber())
                .setEvent(eventMapper.toEntity(ticketDto.getEvent()));
    }
}
