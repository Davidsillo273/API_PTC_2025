package DevSGMA_PTC.SGMA_PTC.Services.WorkOrders;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrder;
import DevSGMA_PTC.SGMA_PTC.Repositories.WorkOrders.WorkOrderRepository;

@Service
public class WorkOrderService {

    private final WorkOrderRepository workOrderRepository;

    public WorkOrderService(WorkOrderRepository workOrderRepository) {
        this.workOrderRepository = workOrderRepository;
    }

    public List<WorkOrder> getAllWorkOrders() {
        return workOrderRepository.findAll();
    }

    public Optional<WorkOrder> getWorkOrderById(Long id) {
        return workOrderRepository.findById(id);
    }

    public WorkOrder createWorkOrder(WorkOrder workOrder) {
        return workOrderRepository.save(workOrder);
    }

    public WorkOrder updateWorkOrder(Long id, WorkOrder updatedWorkOrder) {
        return workOrderRepository.findById(id)
                .map(existingWorkOrder -> {
                    existingWorkOrder.setVehicle(updatedWorkOrder.getVehicle());
                    existingWorkOrder.setAcademicYear(updatedWorkOrder.getAcademicYear());
                    existingWorkOrder.setInstructor(updatedWorkOrder.getInstructor());
                    existingWorkOrder.setStudentName(updatedWorkOrder.getStudentName());
                    existingWorkOrder.setStudentId(updatedWorkOrder.getStudentId());
                    existingWorkOrder.setOperation(updatedWorkOrder.getOperation());
                    existingWorkOrder.setModule(updatedWorkOrder.getModule());
                    existingWorkOrder.setMaintenanceType(updatedWorkOrder.getMaintenanceType());
                    existingWorkOrder.setEstimatedTime(updatedWorkOrder.getEstimatedTime());
                    existingWorkOrder.setEntryTime(updatedWorkOrder.getEntryTime());
                    existingWorkOrder.setExitTime(updatedWorkOrder.getExitTime());
                    existingWorkOrder.setOwnerName(updatedWorkOrder.getOwnerName());
                    existingWorkOrder.setOwnerDui(updatedWorkOrder.getOwnerDui());
                    existingWorkOrder.setStatus(updatedWorkOrder.getStatus());
                    return workOrderRepository.save(existingWorkOrder);
                })
                .orElse(null);
    }

    public boolean deleteWorkOrder(Long id) {
        if (workOrderRepository.existsById(id)) {
            workOrderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
