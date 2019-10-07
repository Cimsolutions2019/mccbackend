package nl.cimsolutions.mccbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.cimsolutions.mccbackend.model.types.VoyagerSensors;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "voyagers")
public class Voyager extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String owner;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Collection<VoyagerSensors> sensors;

    @JsonIgnore
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

}
