package nl.cimsolutions.mccbackend.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import nl.cimsolutions.mccbackend.model.AuditModel;
import nl.cimsolutions.mccbackend.model.DataSource;
import nl.cimsolutions.mccbackend.model.Research;
import nl.cimsolutions.mccbackend.model.Voyager;
import nl.cimsolutions.mccbackend.model.types.ResearchStatus;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ResearchResponse {

    private Long id;

    private String name;

    private String researchArea;

    private String description;

    private ResearchStatus status = ResearchStatus.CREATED;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date endDate;

    private Set<Voyager> voyagers;

    private Set<DataSource> dataSources;

    private Date createdAt;

    private Date updatedAt;

    public ResearchResponse(Research research) {
        this.id = research.getId();
        this.name = research.getName();
        this.researchArea = research.getResearchArea();
        this.description = research.getDescription();
        this.status = research.getStatus();
        this.startDate = research.getStartDate();
        this.endDate = research.getEndDate();
        this.voyagers = research.getVoyagers();
        this.dataSources = research.getDataSources();
        this.createdAt = research.getCreatedAt();
        this.updatedAt = research.getUpdatedAt();
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

    public String getResearchArea() {
        return researchArea;
    }

    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ResearchStatus getStatus() {
        return status;
    }

    public void setStatus(ResearchStatus status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<Voyager> getVoyagers() {
        return voyagers;
    }

    public void setVoyagers(Set<Voyager> voyagers) {
        this.voyagers = voyagers;
    }

    public Set<DataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(Set<DataSource> dataSources) {
        this.dataSources = dataSources;
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
