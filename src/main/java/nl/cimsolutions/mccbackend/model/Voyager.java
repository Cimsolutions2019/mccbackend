package nl.cimsolutions.mccbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import nl.cimsolutions.mccbackend.model.types.VoyagerSensors;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "voyagers")
public class Voyager extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Collection<VoyagerSensors> sensors;

    @ManyToMany(mappedBy = "voyagers")
    private Set<Research> researches = new HashSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="voyager_id")
    private List<Location> locations = new ArrayList<>();

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean active = false;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean inResearch = false;

    public Voyager(String name) {
        this.name = name;
    }

    public Voyager() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void addLocation(Location location) {
        this.locations.add(location);
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getInResearch() {
        return inResearch;
    }

    public void setInResearch(Boolean inResearch) {
        this.inResearch = inResearch;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<VoyagerSensors> getSensors() {
        return sensors;
    }

    public void setSensors(Collection<VoyagerSensors> sensors) {
        this.sensors = sensors;
    }

    public Set<Research> getResearches() {
        return researches;
    }

    public void setResearches(Set<Research> researches) {
        this.researches = researches;
    }
}
