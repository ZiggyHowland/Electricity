package nu.hovland.electricity.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name="Meeter")
public class Meeter {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne
    @JoinColumn(name="locationId", nullable = false)
    private Location location;


}
