package DevSGMA_PTC.SGMA_PTC.Models.DTO.ModuleOperation;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ModuleOperationDTO {


    private Number entryId;

    private Long entryTime;

    private Number operationId;

    private Number status;
}
