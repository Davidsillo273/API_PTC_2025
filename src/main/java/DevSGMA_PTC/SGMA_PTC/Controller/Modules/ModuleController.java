package DevSGMA_PTC.SGMA_PTC.Controller.Modules;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.Modules.ModuleRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Modules.ModuleResponseDTO;
import DevSGMA_PTC.SGMA_PTC.Services.Modules.ModuleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

    private final ModuleService service;

    public ModuleController(ModuleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ModuleResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuleResponseDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ModuleResponseDTO> create(@Valid @RequestBody ModuleRequestDTO dto) {
        ModuleResponseDTO created = service.save(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModuleResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ModuleRequestDTO dto) {
        ModuleResponseDTO updated = service.update(id, dto);
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
