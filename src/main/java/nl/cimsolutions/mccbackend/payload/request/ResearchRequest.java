package nl.cimsolutions.mccbackend.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResearchRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String researchArea;

    @NotBlank
    private String description;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date startDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date endDate;

    private List<Integer> voyagerIds = new ArrayList<>();
    
    private List<Integer> dataSourceIds = new ArrayList<>();

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

    public List<Integer> getVoyagerIds() {
        return voyagerIds;
    }

    public void setVoyagerIds(List<Integer> voyagerIds) {
        this.voyagerIds = voyagerIds;
    }

    public List<Integer> getDataSourceIds() {
        return dataSourceIds;
    }

    public void setDataSourceIds(List<Integer> dataSourceIds) {
        this.dataSourceIds = dataSourceIds;
    }
}
