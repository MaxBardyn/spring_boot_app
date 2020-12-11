package ul.lviv.iot.springjpa.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "ticket")
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private Integer price;

    @Column(name = "sector")
    private String sector;

    @Column(name = "place_number")
    private String placeNumber;

    @OneToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    private Event event;

    @ManyToOne
    @ToString.Exclude
    private TicketOffice ticketOffice;

    @ManyToOne
    @ToString.Exclude
    private User user;
}
