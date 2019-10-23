package nl.cimsolutions.mccbackend.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "weatherstations")
public class WeatherStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String stationNumber;

    @NotNull
    private double longitude;

    @NotNull
    private double latitude;

    @NotNull
    private double altitude;

    public WeatherStation(String name, String stationNumber, double longitude, double latitude) {
        this.name = name;
        this.stationNumber = stationNumber;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
