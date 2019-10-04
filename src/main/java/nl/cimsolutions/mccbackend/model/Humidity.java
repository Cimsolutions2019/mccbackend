package nl.cimsolutions.mccbackend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "humidity")
public class Humidity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private double value;

    public Humidity(int value) {
        this.value = value;
    }

    public Humidity() {}

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
