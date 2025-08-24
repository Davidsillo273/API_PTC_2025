package DevSGMA_PTC.SGMA_PTC.Controllers.Modules;

import DevSGMA_PTC.SGMA_PTC.Exceptions.Modules.ExceptionModuleOperationNotFound;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Modules.ExceptionModuleOperationNotRegister;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Modules.ModuleOperationDTO;
import DevSGMA_PTC.SGMA_PTC.Services.Modules.ModuleOperationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/moduleOperations")
@CrossOrigin
public class ModuleOperationController {

    @Autowired
    private ModuleOperationService service;

    // Obtener todas las operaciones de módulos con paginación
    @GetMapping("/getAllOperations")
    public ResponseEntity<Page<ModuleOperationDTO>> getAllOperations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        if (size <= 0 || size > 50) {
            // Tamaño de página inválido
            return ResponseEntity.badRequest().body(null);
        }

        Page<ModuleOperationDTO> operations = service.getAllOperations(page, size);

        if (operations == null || operations.isEmpty()) {
            // No hay operaciones registradas
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(operations);
    }

    // Crear una nueva operación de módulo
    @PostMapping("/newOperation")
    public ResponseEntity<Map<String, Object>> insertOperation(@Valid @RequestBody ModuleOperationDTO dto, HttpServletRequest request) {
        try {
            ModuleOperationDTO response = service.insert(dto);

            if (response == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "Error", "Inserción incorrecta",
                        "Estatus", "Inserción incorrecta",
                        "Descripción", "Verifique los valores"
                ));
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "Estado", "Completado",
                    "data", response
            ));

        } catch (ExceptionModuleOperationNotRegister e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "status", "error",
                            "message", "Error al registrar la operación",
                            "detail", e.getMessage()
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "status", "error",
                            "message", "Error inesperado",
                            "detail", e.getMessage()
                    ));
        }
    }

    // Actualizar una operación existente
    @PutMapping("/updateOperation/{id}")
    public ResponseEntity<?> updateOperation(
            @PathVariable Long id,
            @Valid @RequestBody ModuleOperationDTO dto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            ModuleOperationDTO updatedOperation = service.update(id, dto);
            return ResponseEntity.ok(updatedOperation);

        } catch (ExceptionModuleOperationNotFound e) {
            // Operación no encontrada
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una operación por ID
    @DeleteMapping("/deleteOperation/{id}")
    public ResponseEntity<Map<String, Object>> deleteOperation(@PathVariable Long id) {
        try {
            if (!service.delete(id)) {
                // Operación no encontrada
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("X-Mensaje-Error", "Operación no encontrada")
                        .body(Map.of(
                                "error", "Not found",
                                "mensaje", "La operación no ha sido encontrada",
                                "timestamp", Instant.now().toString()
                        ));
            }

            // Eliminación exitosa
            return ResponseEntity.ok().body(Map.of(
                    "status", "Proceso completado",
                    "message", "Operación eliminada exitosamente"
            ));

        } catch (Exception e) {
            // Error inesperado
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "Error",
                    "message", "Error al eliminar la operación",
                    "detail", e.getMessage()
            ));
        }
    }
}
