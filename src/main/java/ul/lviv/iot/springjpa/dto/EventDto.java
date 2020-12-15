package ul.lviv.iot.springjpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


@Data
@Accessors(chain = true)
public class EventDto {


    private Long id;

    private String name;

    private TicketDto ticket;

    private LocationDto location;

    private List<ArtistDto> artist;
}
