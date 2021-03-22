package nu.hovland.electricity.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    List<Meter> meters;


    public Location(Long id, String street, String houseNumber, String houseSection, String postCode) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.houseSection = houseSection;
        this.postCode = postCode;
        meters = new ArrayList<>();
    }

    public void addMeter(Meter meter) {
        meters.add(meter);
    }

    public String toString() {
        return String.format("%s %s (%s)", street, houseNumber, postCode);
    }


    // Be aware of the equals(). .value() (auto-unboxing) Wrapper vs primitive


}
