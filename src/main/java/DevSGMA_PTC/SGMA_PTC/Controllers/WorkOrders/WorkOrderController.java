package DevSGMA_PTC.SGMA_PTC.Controllers.WorkOrders;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrder;
import DevSGMA_PTC.SGMA_PTC.Services.WorkOrders.WorkOrderService;
import DevSGMA_PTC.SGMA_PTC.Services.Vehicles.VehicleService;
import DevSGMA_PTC.SGMA_PTC.Services.Users.UsuarioService;
import DevSGMA_PTC.SGMA_PTC.Services.Modules.ModuleService;
import DevSGMA_PTC.SGMA_PTC.Services.ModuleOperations.ModuleOperationService;

@RestController
@RequestMapping("/api/workorders")
public class WorkOrderController {

    private final WorkOrderService workOrderService;
    private final VehicleService vehicleService;
    private final UsuarioService usuarioService;
    private final ModuleService moduleService;
    private final ModuleOperationService moduleOperationService;

    public WorkOrderController(
            WorkOrderService workOrderService,
            VehicleService vehicleService,
            UsuarioService usuarioService,
            ModuleService moduleService,
            ModuleOperationService moduleOperationService) {
        this.workOrderService = workOrderService;
        this.vehicleService = vehicleService;
        this.usuarioService = usuarioService;
        this.moduleService = moduleService;
        this.moduleOperationService = moduleOperationService;
    }

    @GetMapping
    public List<WorkOrder> getAllWorkOrders() {
        return workOrderService.getAllWorkOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkOrder> getWorkOrderById(@PathVariable Long id) {
        Optional<WorkOrder> workOrder = workOrderService.getWorkOrderById(id);
        return workOrder.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<WorkOrder> createWorkOrder(@RequestBody WorkOrder workOrder) {
        if (workOrder.getVehicle() == null || workOrder.getOperation() == null || workOrder.getModule() == null) {
            return ResponseEntity.badRequest().build();
        }
        WorkOrder created = workOrderService.createWorkOrder(workOrder);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkOrder> updateWorkOrder(@PathVariable Long id, @RequestBody WorkOrder workOrder) {
        WorkOrder updated = workOrderService.updateWorkOrder(id, workOrder);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkOrder(@PathVariable Long id) {
        boolean deleted = workOrderService.deleteWorkOrder(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<WorkOrder> patchWorkOrder(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<WorkOrder> optionalWorkOrder = workOrderService.getWorkOrderById(id);
        if (optionalWorkOrder.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        WorkOrder workOrder = optionalWorkOrder.get();

        updates.forEach((key, value) -> {
            switch (key) {
                case "vehicleId":
                    Long vehicleId = parseLong(value);
                    if (vehicleId != null) {
                        vehicleService.getVehicleById(vehicleId).ifPresent(workOrder::setVehicle);
                    }
                    break;
                case "academicYear":
                    workOrder.setAcademicYear((String) value);
                    break;
                case "instructorId":
                    Long instructorId = parseLong(value);
                    if (instructorId != null) {
                        usuarioService.getUsuarioById(instructorId).ifPresent(workOrder::setInstructor);
                    }
                    break;
                case "studentName":
                    workOrder.setStudentName((String) value);
                    break;
                case "studentId":
                    workOrder.setStudentId((String) value);
                    break;
                case "operationId":
                    Long operationId = parseLong(value);
                    if (operationId != null) {
                        moduleOperationService.getOperationById(operationId).ifPresent(workOrder::setOperation);
                    }
                    break;
                case "moduleId":
                    Long moduleId = parseLong(value);
                    if (moduleId != null) {
                        moduleService.getModuleById(moduleId).ifPresent(workOrder::setModule);
                    }
                    break;
                case "maintenanceType":
                    workOrder.setMaintenanceType((String) value);
                    break;
                case "estimatedTime":
                    workOrder.setEstimatedTime((String) value);
                    break;
                case "entryTime":
                    if (value instanceof String) {
                        workOrder.setEntryTime(java.sql.Timestamp.valueOf((String) value));
                    }
                    break;
                case "exitTime":
                    if (value instanceof String) {
                        workOrder.setExitTime(java.sql.Timestamp.valueOf((String) value));
                    }
                    break;
                case "ownerName":
                    workOrder.setOwnerName((String) value);
                    break;
                case "ownerDui":
                    workOrder.setOwnerDui((String) value);
                    break;
                case "status":
                    workOrder.setStatus((String) value);
                    break;
            }
        });

        WorkOrder updated = workOrderService.createWorkOrder(workOrder);
        return ResponseEntity.ok(updated);
    }

    private Long parseLong(Object value) {
        if (value instanceof Integer) return ((Integer) value).longValue();
        if (value instanceof Long) return (Long) value;
        if (value instanceof String) {
            try {
                return Long.parseLong((String) value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}
