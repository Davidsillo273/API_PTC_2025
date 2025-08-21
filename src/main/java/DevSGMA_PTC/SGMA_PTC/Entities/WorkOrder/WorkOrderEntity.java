package DevSGMA_PTC.SGMA_PTC.Entities.WorkOrder;

import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.VehicleEntity;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "TBWORKORDERS")
public class WorkOrderEntity {

    @Id
    private Number workOrderId;


    private VehicleEntity vehicleId;

    private String academicYear;








}
