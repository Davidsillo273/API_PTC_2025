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
                .map(wo -> {
                    wo.setVehicle(updatedWorkOrder.getVehicle());
                    wo.setAcademicYear(updatedWorkOrder.getAcademicYear());
                    wo.setInstructor(updatedWorkOrder.getInstructor());
                    wo.setStudentName(updatedWorkOrder.getStudentName());
                    wo.setStudentId(updatedWorkOrder.getStudentId());
                    wo.setOperation(updatedWorkOrder.getOperation());
                    wo.setModule(updatedWorkOrder.getModule());
                    wo.setMaintenanceType(updatedWorkOrder.getMaintenanceType());
                    wo.setEstimatedTime(updatedWorkOrder.getEstimatedTime());
                    wo.setEntryTime(updatedWorkOrder.getEntryTime());
                    wo.setExitTime(updatedWorkOrder.getExitTime());
                    wo.setOwnerName(updatedWorkOrder.getOwnerName());
                    wo.setOwnerDui(updatedWorkOrder.getOwnerDui());
                    wo.setStatus(updatedWorkOrder.getStatus());
                    return workOrderRepository.save(wo);
                }).orElse(null);
    }

    public boolean deleteWorkOrder(Long id) {
        if(workOrderRepository.existsById(id)) {
            workOrderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
