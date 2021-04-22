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
@RequestMapping(value="/v1/meters")
@CrossOrigin
@Api(tags = "Meters v1", value="TestMeters", description = "API related to meters")
public class MeterControllerV1 {
    @Autowired
    private MeterService service;

    @Autowired
    private LocationService locationService;

    @ApiOperation(value="Get all meters")
    @GetMapping(value="", produces={"application/json", "application/xml"})
    public ResponseEntity<Collection<Meter>> getMeters(){
        Collection<Meter> meters = service.findAll();

        if (meters != null) {
            return ResponseEntity.ok().body(meters);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    @ApiOperation(value="Get one specific meter")
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


    @ApiOperation(value="Update meter")
    @PutMapping(
            value="/{id}",
            consumes={"application/json", "application/xml"},
            produces={"application/json", "application/xml"})
    public ResponseEntity<Void> updateMeter(@PathVariable Long id, @RequestBody Meter meter) {
        Meter m = service.findById(id);
        if (m == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            meter.setLocation(m.getLocation());
            service.updateMeter(meter);
            return ResponseEntity.ok().build();
        }
    }


    @ApiOperation(value="Delete meter")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeter(@PathVariable Long id) {
        try {
            service.deleteMeter(id);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
