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
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date time;

    private String year;

    private String month;

    private String day;

    private String hour;

    private String minute;

    private String second;

}
