package nu.hovland.electricity.controllers;

import nu.hovland.electricity.models.Meeter;
import nu.hovland.electricity.services.LocationService;
import nu.hovland.electricity.services.MeeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/meeters")
public class TestController {
    @Autowired
    private MeeterService service;

    @Autowired
    private LocationService locationService;

    @GetMapping(value="/", produces={"application/json", "application/xml"})
    public ResponseEntity<Collection<Meeter>> getMeetersByLocationId(
            @RequestParam(value="locationId", required = false) Long locationId
    ){
        Collection<Meeter> meeters = new ArrayList<>();

        if (locationId == null) {
            meeters = service.findAll();
        }
        else {
            meeters = service.findByLocation(locationId);
        }

        if (meeters == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(meeters);
        }
    }


    @GetMapping(value="/{id}", produces={"application/json", "application/xml"})
    public ResponseEntity<Meeter> getMeeterById(@PathVariable Long id) {
        Meeter meeter = service.findById(id);
        if (meeter == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(meeter);
        }
    }


    @PostMapping(
            value="/location/{locationId}",
            consumes={"application/json", "application/xml"},
            produces={"application/json", "application/xml"})
    public ResponseEntity<Meeter> insertMeeter(@RequestBody Meeter meeter, @PathVariable Long locationId) {
        try {
            meeter.setLocation(locationService.findLocationById(locationId));
            Meeter m = service.addNewMeeter(meeter);
            URI uri = URI.create("/meeters/location/" + m.getId());
            return ResponseEntity.created(uri).body(m);
        }
        catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error message", e);
            //return ResponseEntity.unprocessableEntity().build(); // TODO: Validate if this is the way to handle it
        }
    }



    @PutMapping(value="/{id}", consumes={"application/json", "application/xml"})
    public ResponseEntity<Void> updateMeeter(@PathVariable Long id, @RequestBody Meeter meeter) {
        try {
            Meeter m = service.findById(id);
            meeter.setLocation(m.getLocation());
            service.updateMeeter(meeter);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error message", e);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeeter(@PathVariable Long id) {
        try {
            service.deleteMeeter(id);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error message", e);
        }
    }








}
