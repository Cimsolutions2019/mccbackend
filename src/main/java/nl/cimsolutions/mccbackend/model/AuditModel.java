package nl.cimsolutions.mccbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
@Data
public abstract class AuditModel implements Serializable {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition= "TIMESTAMP WITH TIME ZONE")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false, columnDefinition= "TIMESTAMP WITH TIME ZONE")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    @LastModifiedDate
    private Date updatedAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
