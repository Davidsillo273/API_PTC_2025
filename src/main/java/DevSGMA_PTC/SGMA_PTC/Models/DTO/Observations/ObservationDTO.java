package DevSGMA_PTC.SGMA_PTC.Models.DTO.Observations;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObservationDTO {
    private Long observacionId;
    private Long workOrderId;
    private String observacion;
    private String imageUrl;
    private Long studentId;
    private String studentName; // optional for responses
}

