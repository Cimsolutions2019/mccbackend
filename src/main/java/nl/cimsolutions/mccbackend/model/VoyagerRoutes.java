package nl.cimsolutions.mccbackend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//@Entity
//@Table(name = "voyagers_routes")
public class VoyagerRoutes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private double longitude;

    @NotNull
    private double latitude;

    @NotNull
    private int priority;

    public VoyagerRoutes(double longitude, double latitude, int priority) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.priority = priority;
    }

    public VoyagerRoutes() {}

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
