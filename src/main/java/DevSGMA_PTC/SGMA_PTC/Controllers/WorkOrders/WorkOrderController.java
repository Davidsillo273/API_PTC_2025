package DevSGMA_PTC.SGMA_PTC.Controllers.WorkOrders;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrder;
import DevSGMA_PTC.SGMA_PTC.Services.WorkOrders.WorkOrderService;
import DevSGMA_PTC.SGMA_PTC.Services.Vehicles.VehicleService;
import DevSGMA_PTC.SGMA_PTC.Services.Users.UserService;
import DevSGMA_PTC.SGMA_PTC.Services.Modules.ModuleService;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/workorders")
public class WorkOrderController {

    private final WorkOrderService workOrderService;
    private final VehicleService vehicleService;
    private final UserService userService;
    private final ModuleService moduleService;

    public WorkOrderController(
            WorkOrderService workOrderService,
            VehicleService vehicleService,
            UserService userService,
            ModuleService moduleService) {
        this.workOrderService = workOrderService;
        this.vehicleService = vehicleService;
        this.userService = userService;
        this.moduleService = moduleService;
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
    public ResponseEntity<?> createWorkOrder(@RequestBody Map<String, Object> request) {
        try {
            WorkOrder workOrder = new WorkOrder();

            workOrder.setVehicle(vehicleService.getVehicleById(Long.parseLong(request.get("vehicleId").toString()))
                    .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado")));

            workOrder.setModule(moduleService.getModuleById(Long.parseLong(request.get("moduleId").toString()))
                    .orElseThrow(() -> new IllegalArgumentException("Módulo no encontrado")));

            workOrder.setAcademicYear(request.get("academicYear").toString());
            workOrder.setMaintenanceType(request.get("maintenanceType").toString());

            if (request.containsKey("operationDescription")) {
                workOrder.setOperationDescription(request.get("operationDescription").toString());
            }

            if (request.containsKey("instructorId")) {
                workOrder.setInstructor(userService.getUser(Long.parseLong(request.get("instructorId").toString())).orElse(null));
            }

            if (request.containsKey("studentName")) {
                workOrder.setStudentName(request.get("studentName").toString());
            }

            if (request.containsKey("studentId")) {
                workOrder.setStudentId(request.get("studentId").toString());
            }

            WorkOrder created = workOrderService.createWorkOrder(workOrder);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al crear la orden de trabajo");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkOrder> updateWorkOrder(@PathVariable Long id, @RequestBody WorkOrder workOrder) {
        WorkOrder updated = workOrderService.updateWorkOrder(workOrder);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkOrder(@PathVariable Long id) {
        workOrderService.deleteWorkOrder(id);
        return ResponseEntity.noContent().build();
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
                        userService.getUser(instructorId).ifPresent(workOrder::setInstructor);
                    }
                    break;
                case "studentName":
                    workOrder.setStudentName((String) value);
                    break;
                case "studentId":
                    workOrder.setStudentId((String) value);
                    break;
                case "operationDescription":
                    workOrder.setOperationDescription((String) value);
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

        WorkOrder updated = workOrderService.updateWorkOrder(workOrder);
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