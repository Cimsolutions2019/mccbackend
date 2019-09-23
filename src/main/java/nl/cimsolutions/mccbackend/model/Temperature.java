package nl.cimsolutions.mccbackend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "temperature")
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private double value;

    public Temperature(double value) {
        this.value = value;
    }

    public Temperature() {}

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
