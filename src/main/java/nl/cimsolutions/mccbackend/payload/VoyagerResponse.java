package nl.cimsolutions.mccbackend.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import nl.cimsolutions.mccbackend.model.AuditModel;
import nl.cimsolutions.mccbackend.model.Research;
import nl.cimsolutions.mccbackend.model.Voyager;
import nl.cimsolutions.mccbackend.model.types.VoyagerSensors;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class VoyagerResponse {

    private Long id;

    private String name;

    private Collection<VoyagerSensors> sensors;

    private Set<Research> researches;

    private Boolean active = false;

    private Boolean inResearch = false;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
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

}

