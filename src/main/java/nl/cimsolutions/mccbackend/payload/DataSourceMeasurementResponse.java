package nl.cimsolutions.mccbackend.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DataSourceMeasurementResponse {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date time;

    private double value;

    public DataSourceMeasurementResponse(Date time, double value) {
        this.time = time;
        this.value = value;
    }
}
