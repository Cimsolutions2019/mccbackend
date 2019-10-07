package nl.cimsolutions.mccbackend.util;
import nl.cimsolutions.mccbackend.model.Location;
import nl.cimsolutions.mccbackend.model.Temperature;
import nl.cimsolutions.mccbackend.model.Voyager;
import nl.cimsolutions.mccbackend.repository.LocationRepository;
import nl.cimsolutions.mccbackend.repository.VoyagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Transactional
public class DummyDataGenerator {

    VoyagerRepository voyagerRepository;

    LocationRepository locationRepository;

    Voyager voyager;

    public DummyDataGenerator(VoyagerRepository voyagerRepository, LocationRepository locationRepository) throws ParseException, IOException {
        this.voyagerRepository = voyagerRepository;
        this.locationRepository = locationRepository;
        this.voyager = new Voyager("Apollo");
        startGenerator();
    }

    public void startGenerator() throws IOException, ParseException {
        List<String> knmiData = readFile("C:\\Users\\M.robert\\Documents\\mccbackend\\src\\main\\resources\\KNMI_20190917_hourly.txt");

        double lon = 4.603;
        double lat = 52.506;
        for(int j = 14;j<knmiData.size();j++) {
            String knmiDataString = knmiData.get(j);
            String[] result = knmiDataString.split(",");
            for(int k = 0;k<result.length;k++) {
                result[k] = result[k].replaceAll("\\s+","");
            }
            int knmiTemp = Integer.parseInt(result[3]) + (int)(Math.random() * 11) - 5;

            LocalTime time;
            LocalDate date;
            if(Integer.parseInt(result[2]) == 24) {
                date = LocalDate.of(Integer.parseInt(result[1].substring(0,4)), Integer.parseInt(result[1].substring(4,6)),
                        Integer.parseInt(result[1].substring(6,8))+1);
                time = LocalTime.of(0, 0);
            }else {
                date = LocalDate.of(Integer.parseInt(result[1].substring(0,4)), Integer.parseInt(result[1].substring(4,6)),
                        Integer.parseInt(result[1].substring(6,8)));
                time = LocalTime.of(Integer.parseInt(result[2]), 0);
            }
            LocalDateTime current = LocalDateTime.of(date, time);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            current = current.minusHours(1);
            current = current.plusMinutes(1);
                        dataGenerator(dtf, current, knmiTemp, lon, lat);
        }
    }

    public void dataGenerator( DateTimeFormatter dtf, LocalDateTime KNMITime, int knmiTemp, double lon, double lat) throws IOException, ParseException {
        for(int i = 0;i<60;i++) {
            String date = dtf.format(KNMITime.plusMinutes(i));
            Date date1 = new SimpleDateFormat("yyyyMMddHHmm").parse(date);
            int temp = (int)(Math.random() * 21) - 10;
            String data = date + ",\"Voyager\":\"1\",\"LON\":" + lon + ",\"LAT\":" + lat + ",\"Temp\":" + (knmiTemp+temp) +"\n";
            saveData(date1, lon, lat, knmiTemp+temp);
        }
        voyagerRepository.save(voyager);

    }

    public void saveData(Date date, double lon, double lat, int temp)
    {
        System.out.println(date);
        Location location = new Location(lon, lat, date);
        double numb1 = temp;
        double numb2 = 10;
        double double_temperature = numb1/numb2;
        Temperature temperature = new Temperature(double_temperature);

        location.setTemperature(temperature);
//        voyager.addLocation(location);
    }

    private static List<String> readFile(String filename)
    {
        List<String> records = new ArrayList<String>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null)
            {
                records.add(line);
            }
            reader.close();
            return records;
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }
}
