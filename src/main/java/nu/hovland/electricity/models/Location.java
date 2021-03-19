package nu.hovland.electricity.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name="Location")
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id = null;
    private String street;
    private String houseNumber;
    private String houseSection;
    private String postCode;

    @OneToMany(mappedBy = "Location")
    Set<Meeter> meeters;

}
