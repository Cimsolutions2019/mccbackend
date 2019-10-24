package nl.cimsolutions.mccbackend.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.cimsolutions.mccbackend.model.types.ResearchTypes;
import nl.cimsolutions.mccbackend.model.types.SensorIntervals;
import nl.cimsolutions.mccbackend.model.types.VoyagerSensors;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SensorIntervalRequest {

    @NotNull
    private VoyagerSensors voyagerSensor;

    @NotNull
    private SensorIntervals sensorInterval;

    @NotNull
    private Long voyagerId;


}
