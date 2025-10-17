package DevSGMA_PTC.SGMA_PTC.Repositories.WorkOrders;

import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrderEntity, Long > {
    // Buscar órdenes por id de estudiante y estado (usando el nombre del campo en la entidad: idStatus)
    List<WorkOrderEntity> findByVehicleId_StudentId_StudentIdAndIdStatus(Long studentId, Long idStatus);

    // Buscar todas las órdenes por id de estudiante (sin filtrar por estado)
    List<WorkOrderEntity> findByVehicleId_StudentId_StudentId(Long studentId);
    //Opcionales
    //Optional<WorkOrderEntity> findByPlate(String plate);
}
