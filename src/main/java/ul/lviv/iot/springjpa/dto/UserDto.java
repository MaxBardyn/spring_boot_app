package ul.lviv.iot.springjpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserDto {


    private Long id;

    private String name;

    private String surname;

    private Integer age;

    private List<TicketDto> tickets;
}
