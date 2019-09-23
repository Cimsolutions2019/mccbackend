package nl.cimsolutions.mccbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "voyagers")
public class Voyager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="voyager_id")
    private List<Location> locations = new ArrayList<>();

    @NotBlank
    private String voyagerLocation;

    @Column(columnDefinition = "boolean default false")
    private Boolean active;

    @Column(columnDefinition = "boolean default false")
    private Boolean inResearch;

    public Voyager(String name, String voyagerLocation) {
        this.name = name;
        this.voyagerLocation = voyagerLocation;
        this.active = true;
        this.inResearch = true;
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

    public String getVoyagerLocation() {
        return voyagerLocation;
    }

    public void setVoyagerLocation(String voyagerLocation) {
        this.voyagerLocation = voyagerLocation;
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
}
