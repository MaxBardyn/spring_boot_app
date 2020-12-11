package ul.lviv.iot.springjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "ticket_office")
public class TicketOffice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticketOffice", orphanRemoval = true)
    @JsonIgnore
    private Set<Ticket> tickets;
}
