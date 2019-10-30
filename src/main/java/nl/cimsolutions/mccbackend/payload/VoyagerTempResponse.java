package nl.cimsolutions.mccbackend.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VoyagerTempResponse {

//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date name;

    private double value;

    public VoyagerTempResponse(Date time, double value) {
        int scale = (int) Math.pow(10, 1);
        this.name = time;
        this.value = Math.round(value * scale) / scale ;
    }

}
