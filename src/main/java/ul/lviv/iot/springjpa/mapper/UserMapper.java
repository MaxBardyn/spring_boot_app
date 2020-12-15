package ul.lviv.iot.springjpa.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ul.lviv.iot.springjpa.dto.TicketDto;
import ul.lviv.iot.springjpa.dto.UserDto;
import ul.lviv.iot.springjpa.entity.Ticket;
import ul.lviv.iot.springjpa.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper implements Mapper<User, UserDto> {


    private final TicketMapper ticketMapper;

    @Override
    public UserDto toDto(User user) {
        return new UserDto()
                .setId(user.getId())
                .setName(user.getName())
                .setSurname(user.getSurname())
                .setAge(user.getAge())
                .setTickets(ticketsToDto(user.getTickets()));
    }

    @Override
    public User toEntity(UserDto userDto) {
        return new User()
                .setId(userDto.getId())
                .setName(userDto.getName())
                .setSurname(userDto.getSurname())
                .setAge(userDto.getAge())
                .setTickets(ticketsToEntity(userDto.getTickets()));
    }

    private List<TicketDto> ticketsToDto(List<Ticket> tickets) {
        return tickets.stream().map(ticketMapper::toDto).collect(Collectors.toList());
    }

    private List<Ticket> ticketsToEntity(List<TicketDto> tickets) {
        return tickets.stream().map(ticketMapper::toEntity).collect(Collectors.toList());
    }
}
