package nl.cimsolutions.mccbackend.controller;

import nl.cimsolutions.mccbackend.exception.ResourceNotFoundException;
import nl.cimsolutions.mccbackend.model.Voyager;
import nl.cimsolutions.mccbackend.payload.VoyagerTempResponse;
import nl.cimsolutions.mccbackend.model.types.VoyagerSensors;
import nl.cimsolutions.mccbackend.repository.LocationRepository;
import nl.cimsolutions.mccbackend.repository.VoyagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/voyagers")
@CrossOrigin
public class VoyagerController {

    @Autowired
    private VoyagerRepository voyagerRepository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("")
    public List<Voyager> getVoyagers() {
        return voyagerRepository.findAll();
    }

    @GetMapping("/Sensors")
    public VoyagerSensors[] getAllVoyagerSensors() {
        VoyagerSensors[] allVoyagerSensors = VoyagerSensors.values();
        return allVoyagerSensors;
    }

    @GetMapping("/{voyagerId}")
    public Optional<Voyager> getVoyager(@PathVariable Long voyagerId) {
        return voyagerRepository.findById(voyagerId);
    }

    //sprint 1 demo
    @GetMapping("/{voyagerId}/temperature")
    public List<VoyagerTempResponse> getTemperaturePerHour(@PathVariable Long voyagerId) {
        return locationRepository.avgTempPerHour(voyagerId);
    }

    @PostMapping("")
    public Voyager createVoyager(@Valid @RequestBody Voyager voyager) {
        return voyagerRepository.save(voyager);
    }

    @DeleteMapping("/{voyagerId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long voyagerId) {
        return voyagerRepository.findById(voyagerId)
                .map(voyager -> {
                    voyagerRepository.delete(voyager);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Voyager not found with id " + voyagerId));
    }

}
