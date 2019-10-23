package nl.cimsolutions.mccbackend.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import nl.cimsolutions.mccbackend.model.types.ResearchTypes;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Getter
@Setter
public class ResearchRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String researchArea;

    @NotBlank
    private String description;

    @NotBlank
    private String owner;

    @NotNull
    private ResearchTypes researchType;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date startDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Madrid")
    private Date endDate;

    private Set<Integer> voyagerIds = new HashSet<>();

    private List<ResearchDataSourceRequest>  researchDataSourceRequest = new ArrayList<>();
}
