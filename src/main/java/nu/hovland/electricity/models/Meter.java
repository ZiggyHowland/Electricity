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

    @ManyToOne
    @JoinColumn(name="location_id", nullable = false)
    @JsonIgnore
    private Location location;
    @Transient // Source: https://dzone.com/articles/introduction-to-spring-data-jpa-part-4-bidirection
    private long locationId;
    public long getLocationId() {
        return location.getId();
    }
    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }


    public String toString() {
        return String.format("Meter[id=%d, %s, Located at: %s]", id, description, location.toString());
    }



}
