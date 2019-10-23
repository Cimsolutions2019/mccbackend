package nl.cimsolutions.mccbackend.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import nl.cimsolutions.mccbackend.model.*;
import nl.cimsolutions.mccbackend.model.types.ResearchStatus;
import nl.cimsolutions.mccbackend.model.types.ResearchTypes;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ResearchResponse {

    private Long id;

    private String name;

    private String researchArea;

    private String description;

    private ResearchStatus status = ResearchStatus.CREATED;

    private String owner;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date endDate;

    private Set<Voyager> voyagers;

    private Set<ResearchDataSourceResponse> dataSources;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date updatedAt;

    private ResearchTypes researchType;

    private List<SensorInterval> sensorIntervals;

    public ResearchResponse(Research research) {
        this.id = research.getId();
        this.name = research.getName();
        this.researchArea = research.getResearchArea();
        this.description = research.getDescription();
        this.status = research.getStatus();
        this.startDate = research.getStartDate();
        this.endDate = research.getEndDate();
        this.voyagers = research.getVoyagers();
        if (research.getDataSources() != null) {
            this.dataSources = research.getDataSources().stream().map(ResearchDataSourceResponse::new).collect(Collectors.toSet());
        }
        this.createdAt = research.getCreatedAt();
        this.updatedAt = research.getUpdatedAt();
        this.owner = research.getOwner();
        this.researchType = research.getResearchType();
        this.sensorIntervals = research.getSensorIntervals();
    }

}
