package DevSGMA_PTC.SGMA_PTC.Services.WorkOrders;

import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrderEntity;
import DevSGMA_PTC.SGMA_PTC.Exceptions.WorkOrders.ExceptionWorkOrdernotRegistred;
import DevSGMA_PTC.SGMA_PTC.Exceptions.WorkOrders.ExceptionWorkOrdernotfound;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.WorkOrders.WorkOrderDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.WorkOrders.WorkOrderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Slf4j
@CrossOrigin
@Service
public class WorkOrderService {

    private WorkOrderRepository repo;

    public Page<WorkOrderDTO> getAllWorkOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<WorkOrderEntity> pageEntity = repo.findAll(pageable);
        return pageEntity.map(this::ConvertirADTO);
    }

    public WorkOrderDTO insert(@Valid WorkOrderDTO json) {
        if (json == null) {
            throw new IllegalArgumentException("La orden de trabajo debe ser llenada con cada campo requerido");
        }
        try {
            WorkOrderEntity objData = ConvertirAEntity(json);
            WorkOrderEntity WorkOrderSaved = repo.save(objData);
            return ConvertirADTO(WorkOrderSaved);
        } catch (Exception e) {
            log.error("Error al regstrar una Orden de Trabajo" + e.getMessage());
            throw new ExceptionWorkOrdernotRegistred("La orden de trabajo no pudo ser registrada");
        }

    }

    public boolean delete(Long id) {
        //1. Verificar la existencia del producto
        WorkOrderEntity existencia = repo.findById(id).orElse(null);
        //2. Si el valor existe lo elimina
        if (existencia != null) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public WorkOrderDTO update(Long id, @Valid WorkOrderDTO json) {
        // 1. Verificar existencia de la orden de trabajo
        WorkOrderEntity workOrderExist = repo.findById(id).orElseThrow(() -> new ExceptionWorkOrdernotfound("Orden de trabajo no encontrada."));
        // 2. Actuaización de campos
        workOrderExist.setWorkOrderId(json.getWorkOrderId());
        workOrderExist.setVehicleId(json.getVehicleId());
        workOrderExist.setModuleId(json.getModuleId());
        workOrderExist.setEstimatedTime(json.getEstimatedTime());
        workOrderExist.setStatus(json.getStatus());
        //3. Actualización del registro
        WorkOrderEntity WorkOrderUpdated = repo.save(workOrderExist);
        //4. Convertir a DTO
        return ConvertirADTO(WorkOrderUpdated);
    }

    // ----------- CONVERTIR A DTO --------------- ///
    private WorkOrderDTO ConvertirADTO(WorkOrderEntity workOrderEntity) {
        WorkOrderDTO dto = new WorkOrderDTO();
        dto.setWorkOrderId(workOrderEntity.getWorkOrderId());
        dto.setVehicleId(workOrderEntity.getVehicleId());
        dto.setModuleId(workOrderEntity.getModuleId());
        dto.setEstimatedTime(workOrderEntity.getEstimatedTime());
        dto.setStatus(workOrderEntity.getStatus());
        return dto;
    }

    // ----------- CONVERTIR A ENTITY --------------- ///
    private WorkOrderEntity ConvertirAEntity(@Valid WorkOrderDTO json) {
        WorkOrderEntity entity = new WorkOrderEntity();
        entity.setWorkOrderId(json.getWorkOrderId());
        entity.setVehicleId(json.getVehicleId());
        entity.setModuleId(json.getModuleId());
        entity.setEstimatedTime(json.getEstimatedTime());
        entity.setStatus(json.getStatus());
        return entity;
    }


}
