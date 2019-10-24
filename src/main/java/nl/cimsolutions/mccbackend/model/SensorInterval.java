package nl.cimsolutions.mccbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.cimsolutions.mccbackend.model.types.ResearchTypes;
import nl.cimsolutions.mccbackend.model.types.SensorIntervals;
import nl.cimsolutions.mccbackend.model.types.VoyagerSensors;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sensor_interval")
public class SensorInterval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VoyagerSensors voyagerSensors;

    @Enumerated(EnumType.STRING)
    private SensorIntervals sensorInterval;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voyager_id")
    private Voyager voyager;

    public SensorInterval(VoyagerSensors voyagerSensors, SensorIntervals sensorInterval) {
        this.voyagerSensors = voyagerSensors;
        this.sensorInterval = sensorInterval;
    }
}
