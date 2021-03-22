package nu.hovland.electricity.controllers;

import nu.hovland.electricity.models.Meeter;
import nu.hovland.electricity.services.LocationService;
import nu.hovland.electricity.services.MeeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(value="location", required = false) Long location
    ){
        Collection<Meeter> meeters = new ArrayList<>();

        if (location == null) {
            meeters = service.findAll();
        }
        else {
            meeters = service.findByLocation(location);
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
    public ResponseEntity<Meeter> insertProduct(@RequestBody Meeter meeter, @PathVariable Long locationId) {
        try {
            meeter.setLocation(locationService.findLocationById(locationId));
            Meeter m = service.addNewMeeter(meeter);
            URI uri = URI.create("test/meeters/" + m.getId());
            return ResponseEntity.created(uri).body(m);
        }
        catch (RuntimeException re) {
            return ResponseEntity.unprocessableEntity().build(); // TODO: Validate if this is the way to handle it
        }
    }


    //@PutMapping // TODO: Continue here!


    //@DeleteMapping








}
