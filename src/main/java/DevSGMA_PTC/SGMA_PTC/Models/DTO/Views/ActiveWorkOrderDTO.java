package DevSGMA_PTC.SGMA_PTC.Models.DTO.Views;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class ActiveWorkOrderDTO {
    private String academicYear;
    private Long workOrderId;
    private String vehicleFullName;
    private byte[] vehicleImage;
    private String performedTask;
    private LocalDateTime exitTime;
}
