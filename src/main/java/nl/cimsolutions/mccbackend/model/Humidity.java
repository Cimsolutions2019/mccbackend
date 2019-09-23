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
    private int value;

    public Humidity(int value) {
        this.value = value;
    }

    public Humidity() {}

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
