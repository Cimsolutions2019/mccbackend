package nl.cimsolutions.mccbackend.datasource;


import nl.cimsolutions.mccbackend.model.DataSource;
import nl.cimsolutions.mccbackend.model.Humidity;
import nl.cimsolutions.mccbackend.model.Location;
import nl.cimsolutions.mccbackend.model.Temperature;
import nl.cimsolutions.mccbackend.repository.DataSourceRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class KnmiCollector {

    @Autowired
    DataSourceRepository dataSourceRepository;

    DataSource dataSource;

    public void collectData() {
        final String uri = "https://meteoserver.nl/api/historie.php?locatie=WIJK_AAN_ZEE&dag=20190901&key=ae7008ea6f";
        this.dataSource = new DataSource("KNMI", uri);

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        try {
            JSONObject booklist = new JSONObject(result);
            System.out.println(booklist);
            JSONArray arr = booklist.getJSONArray("waarneming");
            for (int i = 0; i < arr.length(); i++) {
                int rv = arr.getJSONObject(i).getInt("rv");
                String datum = arr.getJSONObject(i).getString("datum");
                Double temp = arr.getJSONObject(i).getDouble("temp");
                int uur = arr.getJSONObject(i).getInt("uur")-1;

                String datetime = datum + "0" + uur + "00";
                Date date = new SimpleDateFormat("yyyyMMddHHmm").parse(datetime);

//                System.out.println(rv +  " " + datetime);
                saveData(date, 4.603, 52.506, temp, rv);
            }

            this.dataSourceRepository.save(dataSource);
            System.out.println("saved");

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void saveData(Date date, double lon, double lat, double temp, int rv)
    {
        Location location = new Location(lon, lat, date);
        Temperature temperature = new Temperature(temp);
        Humidity humidity = new Humidity(rv);


        location.setTemperature(temperature);
        location.setHumidity(humidity);
        System.out.println(location.getHumidity());
        dataSource.getLocations().add(location);
    }

}
