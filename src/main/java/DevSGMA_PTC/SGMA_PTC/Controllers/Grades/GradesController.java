package DevSGMA_PTC.SGMA_PTC.Controllers.Grades;

import DevSGMA_PTC.SGMA_PTC.Exceptions.Grades.ExceptionGradeDontRegister;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Grades.ExceptionGradeNotFound;
import DevSGMA_PTC.SGMA_PTC.Models.ApiResponse.ApiResponse;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Grades.GradeDTO;
import DevSGMA_PTC.SGMA_PTC.Services.Grades.GradeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/grades")
public class GradesController {

    @Autowired
    private GradeService gradeService;

    //*** MÉTODO PARA OBTENER TODOS LOS GRADOS ***\\
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<GradeDTO>>> getAllGrades() {
        List<GradeDTO> grades = gradeService.getAllGrades();
        if (grades == null || grades.isEmpty()) {
            throw new ExceptionGradeNotFound("No se encontraron grados registrados");
        }
        return ResponseEntity.ok(ApiResponse.success("Grados consultados correctamente", grades));
    }

    //*** MÉTODO PARA INSERTAR UN NUEVO GRADO ***\\
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<GradeDTO>> insertGrade(@Valid @RequestBody GradeDTO dto) {
        if (dto == null) {
            throw new ExceptionGradeDontRegister("Error al recibir la información del grado");
        }

        GradeDTO savedGrade = gradeService.insert(dto);
        if (savedGrade == null) {
            throw new ExceptionGradeDontRegister("No se pudo registrar el grado");
        }

        return ResponseEntity.ok(ApiResponse.success("Grado registrado exitosamente", savedGrade));
    }

    //*** MÉTODO PARA ACTUALIZAR UN GRADO EXISTENTE ***\\
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateGrade(@Valid @PathVariable Long id, @RequestBody GradeDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            GradeDTO updated = gradeService.update(id, dto);
            return ResponseEntity.ok(ApiResponse.success("Grado actualizado correctamente", updated));
        } catch (ExceptionGradeNotFound e) {
            return ResponseEntity.status(404).body(Map.of(
                    "Error", "Not Found",
                    "Message", e.getMessage(),
                    "Timestamp", Instant.now().toString()
            ));
        } catch (ExceptionGradeDontRegister e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "Error", "No se pudo actualizar",
                    "Message", e.getMessage(),
                    "Timestamp", Instant.now().toString()
            ));
        }
    }

    //*** MÉTODO PARA ELIMINAR UN GRADO POR ID ***\\
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteGrade(@PathVariable Long id) {
        try {
            boolean deleted = gradeService.delete(id);
            if (!deleted) {
                return ResponseEntity.status(404).body(Map.of(
                        "Error", "Not Found",
                        "Message", "Grado no encontrado",
                        "Timestamp", Instant.now().toString()
                ));
            }
            return ResponseEntity.ok(Map.of(
                    "Status", "Proceso completado",
                    "Message", "Grado eliminado exitosamente"
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "Status", "Error",
                    "Message", "Error al eliminar el grado",
                    "Detail", e.getMessage()
            ));
        }
    }
}
