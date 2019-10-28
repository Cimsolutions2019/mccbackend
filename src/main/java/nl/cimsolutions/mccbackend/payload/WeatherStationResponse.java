package nl.cimsolutions.mccbackend.payload;

import lombok.Getter;
import lombok.Setter;
import nl.cimsolutions.mccbackend.model.WeatherStation;
import nl.cimsolutions.mccbackend.util.DistanceCalculator;

@Getter
@Setter
public class WeatherStationResponse {

    WeatherStation weatherStation;

    double km;

    public WeatherStationResponse(WeatherStation weatherStation, double lon, double lat) {
        this.weatherStation = weatherStation;
        this.km = DistanceCalculator.calculateDistance(lat, lon , weatherStation.getLatitude(), weatherStation.getLongitude());
    }
}
