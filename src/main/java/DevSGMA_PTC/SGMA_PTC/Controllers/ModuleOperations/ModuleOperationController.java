package DevSGMA_PTC.SGMA_PTC.Controllers.ModuleOperations;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import DevSGMA_PTC.SGMA_PTC.Entities.ModuleOperations.ModuleOperation;
import DevSGMA_PTC.SGMA_PTC.Services.ModuleOperations.ModuleOperationService;

@RestController
@RequestMapping("/api/moduleoperations")
public class ModuleOperationController {

    private final ModuleOperationService moduleOperationService;

    public ModuleOperationController(ModuleOperationService moduleOperationService) {
        this.moduleOperationService = moduleOperationService;
    }

    @GetMapping
    public List<ModuleOperation> getAllOperations() {
        return moduleOperationService.getAllOperations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuleOperation> getOperationById(@PathVariable Long id) {
        Optional<ModuleOperation> operation = moduleOperationService.getOperationById(id);
        return operation.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ModuleOperation createOperation(@RequestBody ModuleOperation operation) {
        return moduleOperationService.createOperation(operation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModuleOperation> updateOperation(@PathVariable Long id, @RequestBody ModuleOperation operation) {
        ModuleOperation updated = moduleOperationService.updateOperation(id, operation);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperation(@PathVariable Long id) {
        boolean deleted = moduleOperationService.deleteOperation(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
