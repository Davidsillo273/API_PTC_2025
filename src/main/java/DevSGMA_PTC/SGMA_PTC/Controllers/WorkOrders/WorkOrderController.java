package DevSGMA_PTC.SGMA_PTC.Controllers.WorkOrders;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrder;
import DevSGMA_PTC.SGMA_PTC.Services.WorkOrders.WorkOrderService;

@RestController
@RequestMapping("/api/workorders")
public class WorkOrderController {

    private final WorkOrderService workOrderService;

    public WorkOrderController(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
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
    public WorkOrder createWorkOrder(@RequestBody WorkOrder workOrder) {
        return workOrderService.createWorkOrder(workOrder);
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
}
