package nu.hovland.electricity.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nu.hovland.electricity.models.Location;
import nu.hovland.electricity.models.Meter;
import nu.hovland.electricity.services.LocationService;
import nu.hovland.electricity.services.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/v1/locations")
@CrossOrigin
@Api(tags = "Locations v1", value="TestLocation", description = "API related to location and inlcuded meters")
public class LocationControllerV1 {
    private final LocationService locationService;
    private final MeterService meterService;

    @Autowired
    public LocationControllerV1(LocationService locationService, MeterService meterService) {
        this.locationService = locationService;
        this.meterService = meterService;
    }

    @ApiOperation(value="Get all locations, each including it's meters")
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


    @ApiOperation(value="Get one specific location, including it's meters")
    @GetMapping(value="/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        Location location = locationService.findById(id);
        if (location != null) {
            return ResponseEntity.ok().body(location);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    @ApiOperation(value="Get all meters for one specific location")
    @GetMapping(value="/{id}/meters")
    public ResponseEntity<Collection<Meter>> getMetersByLocationId(
            @PathVariable Long id )
    {
        Collection<Meter> meters = meterService.findByLocation(id);

        if (meters != null) {
            return ResponseEntity.ok().body(meters);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    @ApiOperation(value="Insert meter into a specific location")
    @PostMapping(value="/{id}/meters")
    public ResponseEntity<Meter> insertMeter(
            @RequestBody Meter meter,
            @PathVariable Long locationId) {

        Meter insertedMeter = locationService.addNewMeterToLocation(locationId, meter);
        if (insertedMeter != null) {
            URI uri = URI.create("/meters/" + insertedMeter.getId());
            return ResponseEntity.created(uri).body(insertedMeter);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
