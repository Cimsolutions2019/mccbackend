package nl.cimsolutions.mccbackend.payload;

import lombok.Getter;
import lombok.Setter;
import nl.cimsolutions.mccbackend.model.Location;
import nl.cimsolutions.mccbackend.model.Voyager;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MeasurementResponse {

    private List<Location> measurements = new ArrayList<>();

    public MeasurementResponse(Voyager voyager) {
        this.measurements = voyager.getLocations();
    }

}
