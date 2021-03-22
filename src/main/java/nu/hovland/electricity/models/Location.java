package nu.hovland.electricity.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "location") // TODO NB: This created an entityManager bean error when having capital 'L'
    List<Meeter> meeters;


    public Location(Long id, String street, String houseNumber, String houseSection, String postCode) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.houseSection = houseSection;
        this.postCode = postCode;
        meeters = new ArrayList<>();
    }

    public void addMeeter(Meeter meeter) {
        meeters.add(meeter);
    }

    public String toString() {
        return String.format("%s %s (%s)", street, houseNumber, postCode);
    }


    // Be aware of the equals(). .value() (auto-unboxing) Wrapper vs primitive


}
