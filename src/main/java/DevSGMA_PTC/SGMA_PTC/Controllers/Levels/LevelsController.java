package DevSGMA_PTC.SGMA_PTC.Controllers.Levels;

import DevSGMA_PTC.SGMA_PTC.Exceptions.Levels.ExceptionLevelDontRegister;
import DevSGMA_PTC.SGMA_PTC.Models.ApiResponse.ApiResponse;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Levels.LevelsDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Levels.LevelsDTO;
import DevSGMA_PTC.SGMA_PTC.Services.Levels.LevelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/levels")
public class LevelsController {

    @Autowired
    private LevelService levelService;

    //*** OBTENER TODOS LOS NIVELES ***//
    @GetMapping("/getDataLevels")
    public ResponseEntity<?> getAllLevels() {
        try {
            return ResponseEntity.ok(ApiResponse.success("Datos consultados correctamente", levelService.getAllLevels()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "Error",
                    "message", "No se pudieron obtener los niveles",
                    "detail", e.getMessage()
            ));
        }
    }

//    //*** CREAR UN NUEVO NIVEL ***//
//    @PostMapping("/addLevel")
//    public ResponseEntity<?> createLevel(@Valid @RequestBody LevelsDTO json, BindingResult bindingResult) {
//        // Validación de campos
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errors = new HashMap<>();
//            bindingResult.getFieldErrors().forEach(error ->
//                    errors.put(error.getField(), error.getDefaultMessage()));
//            return ResponseEntity.badRequest().body(errors);
//        }
//
//        try {
//            LevelDTO levelSaved = levelService.insert(json);
//            return ResponseEntity.ok(ApiResponse.success("Nivel registrado exitosamente", levelSaved));
//        } catch (ExceptionLevelDontRegister e) {
//            return ResponseEntity.badRequest().body(Map.of(
//                    "status", "Error",
//                    "message", e.getMessage()
//            ));
//        }
//    }
//
//    //*** ACTUALIZAR UN NIVEL EXISTENTE ***//
//    @PutMapping("/updateLevel/{id}")
//    public ResponseEntity<?> updateLevel(@Valid @PathVariable Long id, @RequestBody LevelDTO json, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errors = new HashMap<>();
//            bindingResult.getFieldErrors().forEach(error ->
//                    errors.put(error.getField(), error.getDefaultMessage()));
//            return ResponseEntity.badRequest().body(errors);
//        }
//
//        try {
//            LevelDTO levelUpdated = levelService.update(id, json);
//            return ResponseEntity.ok(ApiResponse.success("Nivel actualizado correctamente", levelUpdated));
//        } catch (ExceptionLevelDontRegister e) {
//            return ResponseEntity.badRequest().body(Map.of(
//                    "status", "Error",
//                    "message", e.getMessage()
//            ));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(Map.of(
//                    "status", "Error",
//                    "message", "Error al modificar el nivel"
//            ));
//        }
//    }
//
//    //*** ELIMINAR UN NIVEL POR ID ***//
//    @DeleteMapping("/deleteLevel/{id}")
//    public ResponseEntity<?> deleteLevel(@PathVariable Long id) {
//        try {
//            boolean deleted = levelService.delete(id);
//            if (!deleted) {
//                return ResponseEntity.badRequest().body(Map.of(
//                        "status", "Error",
//                        "message", "Nivel no encontrado"
//                ));
//            }
//            return ResponseEntity.ok(Map.of(
//                    "status", "Proceso completado",
//                    "message", "Nivel eliminado exitosamente"
//            ));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(Map.of(
//                    "status", "Error",
//                    "message", "Error al eliminar el nivel",
//                    "detail", e.getMessage()
//            ));
//        }
//    }
}
