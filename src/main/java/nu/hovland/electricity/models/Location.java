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

    @OneToMany(mappedBy = "location") // TODO NB: This created an entityManager bean error when having capital 'L'
    Set<Meeter> meeters;

    public String toString() {
        return String.format("%s %s (%s)", street, houseNumber, postCode);
    }
}
