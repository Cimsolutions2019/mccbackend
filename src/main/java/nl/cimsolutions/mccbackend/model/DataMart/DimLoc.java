package nl.cimsolutions.mccbackend.model.DataMart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@Table(name = "dim_loc")
public class DimLoc {

    @Id
    private long id;

    private String name;

    @Column(
            columnDefinition = "text"
    )
    private double longitude;

    @Column(
            columnDefinition = "text"
    )
    private double latitude;

    @Column(
            columnDefinition = "text"
    )
    private double altitude;

}
