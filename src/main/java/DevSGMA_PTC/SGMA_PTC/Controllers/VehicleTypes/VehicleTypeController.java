package DevSGMA_PTC.SGMA_PTC.Controllers.VehicleTypes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import DevSGMA_PTC.SGMA_PTC.Entities.VehicleTypes.VehicleType;
import DevSGMA_PTC.SGMA_PTC.Services.VehicleTypes.VehicleTypeService;

@RestController
@RequestMapping("/api/vehicletypes")
public class VehicleTypeController {

    private final VehicleTypeService vehicleTypeService;

    public VehicleTypeController(VehicleTypeService vehicleTypeService) {
        this.vehicleTypeService = vehicleTypeService;
    }

    @GetMapping
    public List<VehicleType> getAllVehicleTypes() {
        return vehicleTypeService.getAllVehicleTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleType> getVehicleTypeById(@PathVariable Long id) {
        Optional<VehicleType> vehicleType = vehicleTypeService.getVehicleTypeById(id);
        return vehicleType.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public VehicleType createVehicleType(@RequestBody VehicleType vehicleType) {
        return vehicleTypeService.createVehicleType(vehicleType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleType> updateVehicleType(@PathVariable Long id, @RequestBody VehicleType vehicleType) {
        VehicleType updated = vehicleTypeService.updateVehicleType(id, vehicleType);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleType(@PathVariable Long id) {
        boolean deleted = vehicleTypeService.deleteVehicleType(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VehicleType> patchVehicleType(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<VehicleType> optionalVehicleType = vehicleTypeService.getVehicleTypeById(id);
        if (optionalVehicleType.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        VehicleType vehicleType = optionalVehicleType.get();

        updates.forEach((key, value) -> {
            if ("name".equals(key)) {
                vehicleType.setName((String) value);
            }
        });

        VehicleType updatedVehicleType = vehicleTypeService.createVehicleType(vehicleType);
        return ResponseEntity.ok(updatedVehicleType);
    }
}
