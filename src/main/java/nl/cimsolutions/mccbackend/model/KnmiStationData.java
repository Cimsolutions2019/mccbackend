package nl.cimsolutions.mccbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@Table(name = "KNMI_station_data")
public class KnmiStationData {

    @NotNull
    private double value;
}
