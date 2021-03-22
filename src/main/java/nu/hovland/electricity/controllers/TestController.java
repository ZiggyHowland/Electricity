package nu.hovland.electricity.controllers;

import nu.hovland.electricity.models.Meter;
import nu.hovland.electricity.services.LocationService;
import nu.hovland.electricity.services.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/meters")
public class TestController {
    @Autowired
    private MeterService service;

    @Autowired
    private LocationService locationService;

    @GetMapping(value="/", produces={"application/json", "application/xml"})
    public ResponseEntity<Collection<Meter>> getMetersByLocationId(
            @RequestParam(value="locationId", required = false) Long locationId
    ){
        Collection<Meter> meters = new ArrayList<>();

        if (locationId == null) {
            meters = service.findAll();
        }
        else {
            meters = service.findByLocation(locationId);
        }

        if (meters == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(meters);
        }
    }


    @GetMapping(value="/{id}", produces={"application/json", "application/xml"})
    public ResponseEntity<Meter> getMeterById(@PathVariable Long id) {
        Meter meter = service.findById(id);
        if (meter == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(meter);
        }
    }


    @PostMapping(
            value="/location/{locationId}",
            consumes={"application/json", "application/xml"},
            produces={"application/json", "application/xml"})
    public ResponseEntity<Meter> insertMeter(@RequestBody Meter meter, @PathVariable Long locationId) {
        try {
            meter.setLocation(locationService.findLocationById(locationId));
            Meter m = service.addNewMeter(meter);
            URI uri = URI.create("/meters/location/" + m.getId());
            return ResponseEntity.created(uri).body(m);
        }
        catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error message", e);
            //return ResponseEntity.unprocessableEntity().build(); // TODO: Validate if this is the way to handle it
        }
    }



    @PutMapping(value="/{id}", consumes={"application/json", "application/xml"})
    public ResponseEntity<Void> updateMeter(@PathVariable Long id, @RequestBody Meter meter) {
        try {
            Meter m = service.findById(id);
            meter.setLocation(m.getLocation());
            service.updateMeter(meter);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error message", e);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeter(@PathVariable Long id) {
        try {
            service.deleteMeter(id);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error message", e);
        }
    }








}
