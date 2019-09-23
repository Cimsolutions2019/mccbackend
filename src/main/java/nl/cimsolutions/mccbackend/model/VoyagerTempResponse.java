package nl.cimsolutions.mccbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class VoyagerTempResponse {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date time;

    private double temp_value;

    public VoyagerTempResponse(Date time, double temp_value) {
        this.time = time;
        this.temp_value = temp_value;
    }


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getTemp_value() {
        return temp_value;
    }

    public void setTemp_value(double temp_value) {
        this.temp_value = temp_value;
    }
}
