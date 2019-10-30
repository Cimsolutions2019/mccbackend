package nl.cimsolutions.mccbackend.model.DataMart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@Table(name = "dim_com")
public class DimCom {

    @Id
    private long id;

    private String name;

}
