package DevSGMA_PTC.SGMA_PTC.Repository.WorkOrders;

import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrder.WorkOrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrderEntity, Long > {
    Page<WorkOrderEntity> findAll(Pageable pageable);
}
