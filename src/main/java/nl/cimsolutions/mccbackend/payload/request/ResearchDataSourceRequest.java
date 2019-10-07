package nl.cimsolutions.mccbackend.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ResearchDataSourceRequest {

    @NotBlank
    private Long dataSourceId;

    @NotBlank
    private Set<Integer> weatherStationsId = new HashSet<>();


}
