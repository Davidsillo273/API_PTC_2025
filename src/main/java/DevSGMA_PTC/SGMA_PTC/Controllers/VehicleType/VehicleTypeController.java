package DevSGMA_PTC.SGMA_PTC.Controllers.VehicleType;

import DevSGMA_PTC.SGMA_PTC.Exceptions.Grades.ExceptionGradeNotFound;
import DevSGMA_PTC.SGMA_PTC.Models.ApiResponse.ApiResponse;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.VehicleType.VehicleTypeDTO;
import DevSGMA_PTC.SGMA_PTC.Services.VehicleType.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicleTypes")
public class VehicleTypeController {

    @Autowired
    private VehicleTypeService vehicleTypeService;


    //*** MÉTODO PARA OBTENER TODOS LOS TIPOS DE VEHÍCULOS ***\\
    @GetMapping("/getAllVehiclesTypes")
    public ResponseEntity<ApiResponse<List<VehicleTypeDTO>>> getAllVehiclesTypes() {
        List<VehicleTypeDTO> vehicleTypeDTO = vehicleTypeService.getAllVehicleTypes();
        if (vehicleTypeDTO == null || vehicleTypeDTO.isEmpty()) {
            throw new ExceptionGradeNotFound("No se encontraron ningún tipo de auto");
        }
        return ResponseEntity.ok(ApiResponse.success("Tipo de auto consultados correctamente", vehicleTypeDTO));
    }

//    public ResponseEntity<List<VehicleTypeDTO>> getAllRoles() {
//        return ResponseEntity.ok(service.getAllRoles());
//    }

//    @GetMapping("/page")
//    public ResponseEntity<Page<VehicleTypeDTO>> getAllPaged(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        return ResponseEntity.ok(service.getAllRoles(page, size));
//    }
//
//
//    @PostMapping
//    public ResponseEntity<VehicleTypeDTO> insert(@Valid @RequestBody VehicleTypeDTO dto) {
//        return ResponseEntity.ok(service.insert(dto));
//    }
//
//
//    @PutMapping("/{id}")
//    public ResponseEntity<VehicleTypeDTO> update(
//            @PathVariable Long id,
//            @Valid @RequestBody VehicleTypeDTO dto
//    ) {
//        return ResponseEntity.ok(service.update(id, dto));
//    }
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> delete(@PathVariable Long id) {
//        boolean deleted = service.delete(id);
//        if (deleted) {
//            return ResponseEntity.ok("Tipo de vehículo eliminado correctamente");
//        } else {
//            return ResponseEntity.badRequest().body("No se encontró el tipo de vehículo con ID: " + id);
//        }
//    }
}
