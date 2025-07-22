package DevSGMA_PTC.SGMA_PTC.Controllers.ModuleOperations;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> createOperation(@RequestBody ModuleOperation operation) {
        try {
            if (operation.getName() == null || operation.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("El nombre de la operación es requerido");
            }
            if (operation.getModule() == null || operation.getModule().getId() == null) {
                return ResponseEntity.badRequest().body("El ID del módulo es requerido");
            }
            ModuleOperation savedOperation = moduleOperationService.createOperation(operation);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedOperation);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la operación");
        }
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

    @PatchMapping("/{id}")
    public ResponseEntity<ModuleOperation> patchOperation(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<ModuleOperation> optionalOperation = moduleOperationService.getOperationById(id);
        if (optionalOperation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ModuleOperation operation = optionalOperation.get();

        updates.forEach((key, value) -> {
            if ("name".equals(key)) {
                operation.setName((String) value);
            }
        });

        ModuleOperation updatedOperation = moduleOperationService.createOperation(operation);
        return ResponseEntity.ok(updatedOperation);
    }
}