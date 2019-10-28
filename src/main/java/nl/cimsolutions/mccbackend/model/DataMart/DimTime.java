package nl.cimsolutions.mccbackend.model.DataMart;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dim_time")
public class DimTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date time;

    private int year;

    private int month;

    private int day;

    private int hour;

    private int minute;

    private int second;

}
