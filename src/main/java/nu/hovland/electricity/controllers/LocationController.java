package nu.hovland.electricity.controllers;

import nu.hovland.electricity.models.Location;
import nu.hovland.electricity.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/locations")
@CrossOrigin
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping(value="")
    public ResponseEntity<Collection<Location>> getLocations() {
        Collection<Location> locations = locationService.findAll();
        if (locations != null ) {
            return ResponseEntity.ok().body(locations);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
