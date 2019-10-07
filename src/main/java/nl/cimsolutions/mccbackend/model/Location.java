package nl.cimsolutions.mccbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private double longitude;

    @NotNull
    private double latitude;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition= "TIMESTAMP WITH TIME ZONE")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date time;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "temperature_id")
    private Temperature temperature;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "humidity_id")
    private Humidity humidity;

    public Location( double longitude, double latitude, Date time) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.time = time;
    }
}
