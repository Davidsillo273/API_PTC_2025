package DevSGMA_PTC.SGMA_PTC.Controllers.Modules;

import DevSGMA_PTC.SGMA_PTC.Exceptions.Modules.ExceptionModuleDontRegister;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Modules.ExceptionModuleNotFound;
import DevSGMA_PTC.SGMA_PTC.Models.ApiResponse.ApiResponse;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Modules.ModuleDTO;
import DevSGMA_PTC.SGMA_PTC.Services.Modules.ModuleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/modules")
public class ModulesController {

    @Autowired
    private ModuleService moduleService;

    //*** MÉTODO PARA OBTENER TODOS LOS MÓDULOS CON PAGINACIÓN ***\\
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<Page<ModuleDTO>>> getAllModules(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (size <= 0 || size > 50) {
            return ResponseEntity.badRequest().body(null);
        }

        Page<ModuleDTO> modules = moduleService.getAllModules(page, size);
        if (modules == null || modules.isEmpty()) {
            throw new ExceptionModuleNotFound("No se encontraron módulos registrados");
        }

        return ResponseEntity.ok(ApiResponse.success("Módulos consultados correctamente", modules));
    }

    //*** MÉTODO PARA INSERTAR UN NUEVO MÓDULO ***\\
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<ModuleDTO>> insertModule(@Valid @RequestBody ModuleDTO dto) {
        if (dto == null) {
            throw new ExceptionModuleDontRegister("Error al recibir la información del módulo");
        }

        ModuleDTO savedModule = moduleService.insert(dto);
        if (savedModule == null) {
            throw new ExceptionModuleDontRegister("No se pudo registrar el módulo");
        }

        return ResponseEntity.ok(ApiResponse.success("Módulo registrado exitosamente", savedModule));
    }

    //*** MÉTODO PARA ACTUALIZAR UN MÓDULO EXISTENTE ***\\
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateModule(@Valid @PathVariable Long id, @RequestBody ModuleDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            ModuleDTO updated = moduleService.update(id, dto);
            return ResponseEntity.ok(ApiResponse.success("Módulo actualizado correctamente", updated));
        } catch (ExceptionModuleNotFound e) {
            return ResponseEntity.status(404).body(Map.of(
                    "Error", "Not Found",
                    "Message", e.getMessage(),
                    "Timestamp", Instant.now().toString()
            ));
        } catch (ExceptionModuleDontRegister e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "Error", "No se pudo actualizar",
                    "Message", e.getMessage(),
                    "Timestamp", Instant.now().toString()
            ));
        }
    }

    //*** MÉTODO PARA ELIMINAR UN MÓDULO POR ID ***\\
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteModule(@PathVariable Long id) {
        try {
            boolean deleted = moduleService.delete(id);
            if (!deleted) {
                return ResponseEntity.status(404).body(Map.of(
                        "Error", "Not Found",
                        "Message", "Módulo no encontrado",
                        "Timestamp", Instant.now().toString()
                ));
            }
            return ResponseEntity.ok(Map.of(
                    "Status", "Proceso completado",
                    "Message", "Módulo eliminado exitosamente"
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "Status", "Error",
                    "Message", "Error al eliminar el módulo",
                    "Detail", e.getMessage()
            ));
        }
    }
}
