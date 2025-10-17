package DevSGMA_PTC.SGMA_PTC.Services.WorkOrders;

import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.VehicleEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrderEntity;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Modules.ExceptionModuleNotFound;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Vehicles.ExceptionVehicleNotFound;
import DevSGMA_PTC.SGMA_PTC.Exceptions.WorkOrders.ExceptionWorkOrdernotRegistred;
import DevSGMA_PTC.SGMA_PTC.Exceptions.WorkOrders.ExceptionWorkOrdernotfound;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.WorkOrders.WorkOrderDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Modules.ModuleRepository;
import DevSGMA_PTC.SGMA_PTC.Repositories.Vehicles.VehicleRepository;
import DevSGMA_PTC.SGMA_PTC.Repositories.WorkOrders.WorkOrderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin
@Service
public class WorkOrderService {

    private final WorkOrderRepository repo;
    private final VehicleRepository vehicleRepository;
    private final ModuleRepository moduleRepository;

    @Autowired
    public WorkOrderService(WorkOrderRepository repo, VehicleRepository vehicleRepository, ModuleRepository moduleRepository) {
        this.repo = repo;
        this.vehicleRepository = vehicleRepository;
        this.moduleRepository = moduleRepository;
    }

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
            WorkOrderEntity workOrderEntity = repo.save(objData);
            return ConvertirADTO(workOrderEntity);
        } catch (Exception e) {
            log.error("Error al registrar una Orden de Trabajo " + e.getMessage());
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

        workOrderExist.setIdStatus(json.getIdStatus());
        //3. Actualización del registro
        WorkOrderEntity WorkOrderUpdated = repo.save(workOrderExist);
        //4. Convertir a DTO
        return ConvertirADTO(WorkOrderUpdated);
    }

    // Obtener órdenes por estudiante y estado
    public Map<String, Object> getWorkOrdersByStudentIdAndStatus(Long studentId, Long status) {
        List<WorkOrderEntity> orders = repo.findByVehicleId_StudentId_StudentIdAndIdStatus(studentId, status);
        List<WorkOrderDTO> dtos = orders.stream().map(this::ConvertirADTO).toList();
        return Map.of(
            "workOrders", dtos,
            "cantidad", dtos.size()
        );
    }

    // Obtener todas las órdenes por estudiante (sin filtrar por estado)
    public Map<String, Object> getWorkOrdersByStudentId(Long studentId) {
        List<WorkOrderEntity> orders = repo.findByVehicleId_StudentId_StudentId(studentId);
        List<WorkOrderDTO> dtos = orders.stream().map(this::ConvertirADTO).toList();
        return Map.of(
            "workOrders", dtos,
            "cantidad", dtos.size()
        );
    }

    private WorkOrderDTO ConvertirADTO(WorkOrderEntity workOrderEntity) {
        WorkOrderDTO dto = new WorkOrderDTO();
        dto.setWorkOrderId(workOrderEntity.getWorkOrderId());

        if (workOrderEntity.getVehicleId() != null) {
            dto.setVehiclePlateNumber(workOrderEntity.getVehicleId().getPlateNumber());
            dto.setVehicleId(workOrderEntity.getVehicleId().getVehicleId());
        }

        if (workOrderEntity.getModuleId() != null) {
            dto.setModuleName(workOrderEntity.getModuleId().getModuleName());
            dto.setModuleId(workOrderEntity.getModuleId().getModuleId());
        }

        dto.setWorkOrderImage(workOrderEntity.getWorkOrdersImage());
        dto.setIdStatus(workOrderEntity.getIdStatus());

        return dto;
    }

    private WorkOrderEntity ConvertirAEntity(@Valid WorkOrderDTO json) {
        WorkOrderEntity entity = new WorkOrderEntity();
        entity.setWorkOrderId(json.getWorkOrderId());

        if (json.getVehicleId() != null) {
            VehicleEntity vehicleEntity = vehicleRepository.findById(json.getVehicleId())
                    .orElseThrow(() -> new ExceptionVehicleNotFound("ID del vehiculo no encontrado"));
            entity.setVehicleId(vehicleEntity);
        }

        if (json.getModuleId() != null) {
            ModuleEntity moduleEntity = moduleRepository.findById(json.getModuleId())
                    .orElseThrow(() -> new ExceptionModuleNotFound("ID del modulo no encontrado"));
            entity.setModuleId(moduleEntity);
        }

        entity.setWorkOrdersImage(json.getWorkOrderImage());
        entity.setIdStatus(json.getIdStatus());

        return entity;
    }



}
