package DevSGMA_PTC.SGMA_PTC.Repositories.WorksOrders;

import DevSGMA_PTC.SGMA_PTC.Models.Entities.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
    List<WorkOrder> findByStatus(WorkOrder.WorkOrderStatus status);
    List<WorkOrder> findByAcademicYearAndStatus(WorkOrder.AcademicYear academicYear, WorkOrder.WorkOrderStatus status);
}
