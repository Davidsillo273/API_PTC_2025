package DevSGMA_PTC.SGMA_PTC.Services.WorksOrders;

import DevSGMA_PTC.SGMA_PTC.Entities.WorksOrders.WorkOrderEntity;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.WorksOrders.WorkOrderRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.WorksOrders.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class WorkOrderService {
    @Autowired
    WorkOrderRepository workOrderRepository;

    // Devuelve todos los roles
    public List<WorkOrderRequestDTO> getallWorkOrders () {
        List<WorkOrderEntity>Orders = workOrderRepository.findAll();
        return Orders.stream()
                .map(this::convertOrdersDTO)
                .collect(Collectors.toList());
    }

        public WorkOrderRequestDTO convertOrdersDTO(WorkOrderEntity entity){
        WorkOrderRequestDTO dto = new WorkOrderRequestDTO();
            dto.setWorkOrderId(entity.getWorkOrderId());
            dto.setVehicleId(entity.getVehicleId());
            dto.setAcademicYear(entity.getAcademicYear());
            dto.setInstructorId(entity.getInstructorId());
            dto.setStudentName(entity.getStudentName());
            dto.setStudentId(entity.getStudentId());
            dto.setOperationId(entity.getOperationId());
            dto.setModuleId(entity.getModuleId());
            dto.setMaintenanceType(entity.getMaintenanceType());
            dto.setEstimatedTime(entity.getEstimatedTime());
            dto.setEntryTime(entity.getEntryTime());
            dto.setExitTime(entity.getExitTime());
            dto.setOwnerName(entity.getOwnerName());
            dto.setOwnerDui(entity.getOwnerDui());
            dto.setStatus(entity.getStatus());

            return dto;
    }
}
