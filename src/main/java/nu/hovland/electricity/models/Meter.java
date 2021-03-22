package nu.hovland.electricity.models;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name="Meter")
public class Meter {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id = null;
    private String description;

    @JsonBackReference
    @ManyToOne(targetEntity = Location.class)
    @JoinColumn(name="locationId", nullable = false)
    private Location location;
    // TODO: Find out how to add locationId as response value


    public String toString() {
        return String.format("Meter[id=%d, %s, Located at: %s]", id, description, location.toString());
    }



}
