package ul.lviv.iot.springjpa.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TicketOfficeDto {

    private Long id;

    private List<TicketDto> tickets;
}
