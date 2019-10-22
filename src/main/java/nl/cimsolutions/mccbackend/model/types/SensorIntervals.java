package nl.cimsolutions.mccbackend.model.types;

public enum SensorIntervals {

    DAY(1),
    DAYPART(8),
    HOURLY(24),
    MINUTELY(1440);

    private final int interval;

    SensorIntervals(int interval) {
        this.interval = interval;
    }
}
