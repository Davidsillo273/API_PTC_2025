package DevSGMA_PTC.SGMA_PTC.Services.WorkOrders;

import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrderEntity;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.WorkOrders.WorkOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import DevSGMA_PTC.SGMA_PTC.Repositories.WorkOrders.WorkOrderRepository;

import java.util.Optional;

@Service
public class WorkOrderService {

   @Autowired
    private WorkOrderRepository workOrderRepository;

    /**
     * Obtiene todas las ordenes de trabajo paginados y los convierte a DTO.
     * @param page Número de página a consulta.
     * @param size Tamaño de la pagina (cantidad de elementos por página).
     * @return Página de usuaros en formato DTO
     */
   public Page<WorkOrderDTO> getAllUsers(int page, int size){
       Pageable pageable = PageRequest.of(page,size);
       Page<WorkOrderEntity> workOrderEntityPage = workOrderRepository.findAll(pageable);
       return workOrderEntityPage.map(this::ConvertToDTO);
    }

    public Optional<WorkOrderEntity> getWorkOrderID(long id){
       return WorkOrderRepository.findById(id);
    }

    private WorkOrderDTO ConvertToDTO(WorkOrderEntity workOrderEntity) {
       WorkOrderDTO dto = new WorkOrderDTO();
       dto.setWorkOrderid(workOrderEntity.getWorkOrderid());
       dto.setVehicleId(workOrderEntity.getVehicleId());
       dto.setAcademicYear(workOrderEntity.getAcademicYear());
       dto.setInstructor(workOrderEntity.getInstructor());
       dto.setStudentName(workOrderEntity.getStudentName());
       dto.setStudentId(workOrderEntity.getStudentId());
       dto.setOperationDescription(workOrderEntity.getOperationDescription());
       dto.setModuleId(workOrderEntity.getModuleId());
       dto.setMaintenanceType(workOrderEntity.getMaintenanceType());
       dto.setEstimatedTime(workOrderEntity.getEstimatedTime());
       dto.setEntryTime(workOrderEntity.getEntryTime());
       dto.setExitTime(workOrderEntity.getExitTime());
       dto.setOwnerName(workOrderEntity.getOwnerName());
       dto.setOwnerDui(workOrderEntity.getOwnerDui());
       dto.setStatus(workOrderEntity.getStatus());
        return dto;
    }

    public WorkOrderDTO


}
