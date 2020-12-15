package ul.lviv.iot.springjpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class TicketDto {


    private Long id;

    private Integer price;

    private String sector;

    @JsonProperty(value = "place_number")
    private String placeNumber;

    @JsonIgnore
    @JsonProperty(value = "event")
    private EventDto event;

    @JsonIgnore
    @JsonProperty(value = "ticket_office")
    private TicketOfficeDto ticketOffice;

    @JsonIgnore
    private UserDto user;
}
