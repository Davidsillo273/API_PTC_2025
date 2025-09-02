package DevSGMA_PTC.SGMA_PTC.Controllers.Entries;

import DevSGMA_PTC.SGMA_PTC.Exceptions.Entries.ExceptionEntryNotFound;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Entries.EntryDTO;
import DevSGMA_PTC.SGMA_PTC.Services.Entries.EntryService;
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
@RequestMapping("/api/Entries")
@CrossOrigin
public class EntryController {

    @Autowired
    private EntryService service;

    @GetMapping("/getAllVehicleEntries")
    private ResponseEntity<Page<EntryDTO>> getDataVehicleEntries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        if (size <= 0 || size > 50){
            ResponseEntity.badRequest().body(Map.of(
                    "status", "El tamaño de la página debe estar entre 1 y 50"
            ));
            return ResponseEntity.ok(null);
        }
        Page<EntryDTO> VehicleEntries = service.getAllVehicleEntries(page, size);
        if (VehicleEntries == null){
            ResponseEntity.badRequest().body(Map.of(
                    "status", "No hay productos registrados"
            ));
        }
        return ResponseEntity.ok(VehicleEntries);
    }

    @PostMapping("/newVehicleEntries")
    private ResponseEntity<Map<String, Object>> insertVehicleEntries(@Valid @RequestBody EntryDTO json, HttpServletRequest request){
        try{
            EntryDTO response =service.createEntry(json);
            if (response == null){
                return ResponseEntity.badRequest().body(Map.of(
                        "Error", "Inserción incorrecta",
                        "Estatus", "Inserción incorrecta",
                        "Descripción", "Verifique que los campos esten correctamente ingresados"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "Estado", "Completado",
                    "data", response
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "status", "error",
                            "message", "error al registrar el ingreso del vehículo",
                            "detail", e.getMessage()
                    ));
        }
    }

    @PutMapping("/updateVehicleEntries/{id}")
    public ResponseEntity<?> modifyVehicleEntries(
            @PathVariable Long id,
            @Valid @RequestBody EntryDTO entryDTO,  // ← Corregí el nombre
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }

        try {
            EntryDTO vehicleEntriesUpdated = service.update(id, entryDTO);
            return ResponseEntity.ok(vehicleEntriesUpdated);  // ← Esto generará el JSON
        } catch (ExceptionEntryNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Mapea este metodo a una petición DELETE con un parámetro de ruta {id}
    @DeleteMapping("/DeleteVehicleEntries/{id}")
    public ResponseEntity<Map<String, Object>> DeleteVehicleEntries(@PathVariable Long id) {
        try {
            // Intenta eliminar la categoria usando objeto 'service'
            // Si el metodo delete retorna false (no encontró la categoria)
            if (!service.delete(id)) {
                // Retorna una respuesta 404 (Not Found) con información detallada
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        // Agrega un header personalizado
                        .header("X-Mensaje-Error", "Registro de ingreso de vehiculos no encontrado")
                        // Cuerpo de la respuesta con detalles del error
                        .body(Map.of(
                                "error", "Not found",  // Tipo de error
                                "mensaje", "La categoria no ha sido encontrada",  // Mensaje descriptivo
                                "timestamp", Instant.now().toString()  // Marca de tiempo del error
                        ));
            }

            // Si la eliminación fue exitosa, retorna 200 (OK) con mensaje de confirmación
            return ResponseEntity.ok().body(Map.of(
                    "status", "Proceso completado",  // Estado de la operación
                    "message", "Categoría eliminada exitosamente"  // Mensaje de éxito
            ));

        } catch (Exception e) {
            // Si ocurre cualquier error inesperado, retorna 500 (Internal Server Error)
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "Error",  // Indicador de error
                    "message", "Error al eliminar la categoría",  // Mensaje general
                    "detail", e.getMessage()  // Detalles técnicos del error (para debugging)
            ));
        }
    }
}

