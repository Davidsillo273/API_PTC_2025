package DevSGMA_PTC.SGMA_PTC.Controllers.Modules;

import DevSGMA_PTC.SGMA_PTC.Exceptions.Modules.ExceptionModuleNotFound;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Modules.ExceptionModuleNotRegister;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Modules.ModuleDTO;
import DevSGMA_PTC.SGMA_PTC.Services.Modules.ModuleService;
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
@RequestMapping("/api/modules")
@CrossOrigin
public class ModuleController {

    @Autowired
    private ModuleService service;

    // Obtener todos los módulos con paginación
    @GetMapping("/getAllModules")
    private ResponseEntity<Page<ModuleDTO>> getAllModules(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        if (size <= 0 || size > 50){
            // Si el tamaño de la página es inválido
            return ResponseEntity.badRequest().body(null);
        }

        Page<ModuleDTO> modules = service.getAllModules(page, size);

        if (modules == null || modules.isEmpty()){
            // Si no hay módulos registrados
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(modules);
    }

    // Crear un nuevo módulo
    @PostMapping("/newModule")
    private ResponseEntity<Map<String, Object>> insertModule(@Valid @RequestBody ModuleDTO json, HttpServletRequest request){
        try{
            ModuleDTO response = service.insert(json);

            if (response == null){
                // Si la inserción falla
                return ResponseEntity.badRequest().body(Map.of(
                        "Error", "Inserción incorrecta",
                        "Estatus", "Inserción incorrecta",
                        "Descripción", "Verifique los valores"
                ));
            }

            // Inserción exitosa
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "Estado", "Completado",
                    "data", response
            ));

        } catch (ExceptionModuleNotRegister e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "status", "error",
                            "message", "Error al registrar módulo",
                            "detail", e.getMessage()
                    ));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "status", "error",
                            "message", "Error inesperado",
                            "detail", e.getMessage()
                    ));
        }
    }

    // Actualizar un módulo existente
    @PutMapping("/updateModule/{id}")
    public ResponseEntity<?> updateModule(
            @PathVariable Long id,
            @Valid @RequestBody ModuleDTO dto,
            BindingResult bindingResult){

        // Validación de errores de los campos
        if (bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        try{
            ModuleDTO updatedModule = service.update(id, dto);
            return ResponseEntity.ok(updatedModule);

        } catch (ExceptionModuleNotFound e){
            // Si no se encuentra el módulo
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un módulo
    @DeleteMapping("/deleteModule/{id}")
    public ResponseEntity<Map<String, Object>> deleteModule(@PathVariable Long id) {
        try {
            if (!service.delete(id)) {
                // Módulo no encontrado
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("X-Mensaje-Error", "Módulo no encontrado")
                        .body(Map.of(
                                "error", "Not found",
                                "mensaje", "El módulo no ha sido encontrado",
                                "timestamp", Instant.now().toString()
                        ));
            }

            // Eliminación exitosa
            return ResponseEntity.ok().body(Map.of(
                    "status", "Proceso completado",
                    "message", "Módulo eliminado exitosamente"
            ));

        } catch (Exception e) {
            // Error inesperado
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "Error",
                    "message", "Error al eliminar el módulo",
                    "detail", e.getMessage()
            ));
        }
    }
}
