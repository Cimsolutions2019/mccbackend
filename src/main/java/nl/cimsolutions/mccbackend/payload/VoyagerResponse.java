package nl.cimsolutions.mccbackend.payload;

import nl.cimsolutions.mccbackend.model.AuditModel;
import nl.cimsolutions.mccbackend.model.Research;
import nl.cimsolutions.mccbackend.model.Voyager;
import nl.cimsolutions.mccbackend.model.types.VoyagerSensors;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class VoyagerResponse {

    private Long id;

    private String name;

    private Collection<VoyagerSensors> sensors;

    private Set<Research> researches;

    private Boolean active = false;

    private Boolean inResearch = false;

    private Date createdAt;

    private Date updatedAt;

    public VoyagerResponse(Voyager voyager) {
        this.id = voyager.getId();
        this.name = voyager.getName();
        this.sensors = voyager.getSensors();
        this.researches = voyager.getResearches();
        this.active = voyager.getActive();
        this.inResearch = voyager.getInResearch();
        this.createdAt = voyager.getCreatedAt();
        this.updatedAt = voyager.getUpdatedAt();
           }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}

