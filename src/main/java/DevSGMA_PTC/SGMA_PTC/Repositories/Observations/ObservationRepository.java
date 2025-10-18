package DevSGMA_PTC.SGMA_PTC.Repositories.Observations;

import DevSGMA_PTC.SGMA_PTC.Entities.Observations.ObservationEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObservationRepository extends JpaRepository<ObservationEntity, Long> {
    List<ObservationEntity> findByWorkOrderId_WorkOrderId(Long workOrderId);
}

