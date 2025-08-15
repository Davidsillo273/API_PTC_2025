package DevSGMA_PTC.SGMA_PTC.Repositories.WorkOrders;

import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrderEntity, Long> {
    Page<WorkOrderEntity> findAll(Pageable pageable);
    List<WorkOrderEntity> findByStatus(String status);
    List<WorkOrderEntity> findByInstructorId(Long instructorId);
    List<WorkOrderEntity> findByStudentId(String studentId);
}
