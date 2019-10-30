package nl.cimsolutions.mccbackend.model.DataMart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.cimsolutions.mccbackend.model.Temperature;

import javax.persistence.*;

//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@Table(name = "fact_source")
public class FactSource {

    @Id
    private long id;

    private String weather_station_id;

    private long data_source_id;

    private String value;

//    private long id_time;
//
//    private long id_location;
//
//    private long id_component;

    @OneToOne()
    @JoinColumn(name = "id_time")
    private DimTime dimTime ;

    @OneToOne()
    @JoinColumn(name = "id_location")
    private DimLoc dimLoc;

    @OneToOne()
    @JoinColumn(name = "id_component")
    private DimCom dimCom;

}
