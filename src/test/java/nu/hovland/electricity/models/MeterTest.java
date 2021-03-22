package nu.hovland.electricity.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeterTest {

    @Test
    public void givenBidirectionRelation_whenSerializing_thenException() throws JsonProcessingException {
        Location location = new Location(1L, "Gate", "1", "D", "1234");
        Meter meter = new Meter(1L, "Hovedmåler", location);
        location.addMeter(meter);

        JsonMappingException thrown = assertThrows(
            JsonMappingException.class, () -> new ObjectMapper().writeValueAsString(meter),
            "Expected writeValueAsString() to throw, but it didn't"
        );
        System.out.println(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("Infinite recursion"));
    }

    @Test
    public void givenBidirectionRelation_whenUsingJacksonReferenceAnnotationWithSerialization_thenCorrect() throws JsonProcessingException {
        final Location location = new Location(1L, "Gate", "1", "D", "1234");
        final Meter meter = new Meter(1L, "Hovedmåler", location);
        location.addMeter(meter);

        final String meeterJson = new ObjectMapper().writeValueAsString(meter);
        final String locationJson = new ObjectMapper().writeValueAsString(location);

        System.out.println(meeterJson);
        assertTrue(meeterJson.contains("Hoved"));
        assertTrue(!meeterJson.contains("Gate"));

        System.out.println(locationJson);
        assertTrue(locationJson.contains("Gate"));
        assertTrue(locationJson.contains("meeters"));
        assertTrue(locationJson.contains("Hoved"));
    }


}