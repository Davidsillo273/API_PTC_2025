package DevSGMA_PTC.SGMA_PTC.Repositories.WorkOrders;

import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
    List<WorkOrder> findByStatus(String status);
    List<WorkOrder> findByInstructorId(Long instructorId);
    List<WorkOrder> findByStudentId(String studentId);
}
