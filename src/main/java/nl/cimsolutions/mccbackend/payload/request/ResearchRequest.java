package nl.cimsolutions.mccbackend.payload.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class ResearchRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String researchArea;

    @NotBlank
    private String description;

    private List<Integer> voyagerIds = new ArrayList<>();

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

    public List<Integer> getVoyagerIds() {
        return voyagerIds;
    }

    public void setVoyagerIds(List<Integer> voyagerIds) {
        this.voyagerIds = voyagerIds;
    }
}
