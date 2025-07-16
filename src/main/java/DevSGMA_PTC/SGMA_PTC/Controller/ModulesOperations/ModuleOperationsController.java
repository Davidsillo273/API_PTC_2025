package DevSGMA_PTC.SGMA_PTC.Controller.ModulesOperations;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.ModulesOperations.ModuleOperationRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.ModulesOperations.ModuleOperationResponseDTO;
import DevSGMA_PTC.SGMA_PTC.Services.ModulesOperations.ModuleOperationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/module-operations")
public class ModuleOperationsController {

    private final ModuleOperationService service;

    public ModuleOperationsController(ModuleOperationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ModuleOperationResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuleOperationResponseDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ModuleOperationResponseDTO> create(@Valid @RequestBody ModuleOperationRequestDTO dto) {
        try {
            ModuleOperationResponseDTO created = service.save(dto);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModuleOperationResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ModuleOperationRequestDTO dto) {
        ModuleOperationResponseDTO updated = service.update(id, dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
