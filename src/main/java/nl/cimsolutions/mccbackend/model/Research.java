package nl.cimsolutions.mccbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import nl.cimsolutions.mccbackend.model.types.ResearchStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "research")
public class Research extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String researchArea;

    @Column(columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private ResearchStatus status = ResearchStatus.CREATED;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition= "TIMESTAMP WITH TIME ZONE")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition= "TIMESTAMP WITH TIME ZONE")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date endDate;

    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="research_voyager",
            joinColumns = @JoinColumn(name="research_id"),
            inverseJoinColumns = @JoinColumn(name="voyager_id")
            )
    private Set<Voyager> voyagers = new HashSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="research_id")
    private List<Location> locations = new ArrayList<>();

    public Research(String name, String researchArea, String description, Date startDate) {
        this.name = name;
        this.researchArea = researchArea;
        this.description = description;
        this.startDate = startDate;
    }

    public Research() {}

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

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }


}

