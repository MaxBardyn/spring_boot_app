package ul.lviv.iot.springjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "event")
public class Event implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "event")
    private Ticket Ticket;

    @ManyToOne
    @ToString.Exclude
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event", orphanRemoval = true)
    @JsonIgnore
    private Set<Artist> artists;
}
