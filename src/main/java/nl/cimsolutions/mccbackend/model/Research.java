package nl.cimsolutions.mccbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.cimsolutions.mccbackend.model.types.ResearchStatus;
import nl.cimsolutions.mccbackend.model.types.ResearchTypes;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "research")
public class Research extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NaturalId
    private String name;

    @NotBlank
    private String researchArea;

    @Column(columnDefinition = "text")
    private String description;

    @NotBlank
    private String owner;

    @Enumerated(EnumType.STRING)
    private ResearchStatus status = ResearchStatus.CREATED;

    @Enumerated(EnumType.STRING)
    private ResearchTypes researchType;

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
    @OneToMany(mappedBy = "research", cascade = CascadeType.ALL)
    private Set<ResearchDataSource> dataSources = new HashSet<>();
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="research_id")
    private List<Location> locations = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="research_id")
    private List<SensorInterval> sensorIntervals = new ArrayList<>();

    public Research(String name, String researchArea, String description, Date startDate, Date endDate, String owner, ResearchTypes researchType) {
        this.name = name;
        this.researchArea = researchArea;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.owner = owner;
        this.researchType = researchType;
    }
}

