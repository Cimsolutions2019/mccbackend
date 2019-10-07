package nl.cimsolutions.mccbackend.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VoyagerTempResponse {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date time;

    private double temp_value;

    public VoyagerTempResponse(Date time, double temp_value) {
        this.time = time;
        this.temp_value = temp_value;
    }

}
