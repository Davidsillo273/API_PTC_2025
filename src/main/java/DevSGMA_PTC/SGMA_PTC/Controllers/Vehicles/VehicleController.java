package DevSGMA_PTC.SGMA_PTC.Controllers.Vehicles;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.Vehicle;
import DevSGMA_PTC.SGMA_PTC.Services.Vehicles.VehicleService;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        return vehicle.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        Vehicle updated = vehicleService.updateVehicle(id, vehicle);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Vehicle> patchVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        Optional<Vehicle> existingOpt = vehicleService.getVehicleById(id);
        if (existingOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Vehicle existing = existingOpt.get();

        if (vehicle.getPlateNumber() != null) existing.setPlateNumber(vehicle.getPlateNumber());
        if (vehicle.getBrand() != null) existing.setBrand(vehicle.getBrand());
        if (vehicle.getModel() != null) existing.setModel(vehicle.getModel());
        if (vehicle.getType() != null) existing.setType(vehicle.getType());
        if (vehicle.getColor() != null) existing.setColor(vehicle.getColor());
        if (vehicle.getCirculationCardNumber() != null) existing.setCirculationCardNumber(vehicle.getCirculationCardNumber());
        if (vehicle.getVehicleImage() != null) existing.setVehicleImage(vehicle.getVehicleImage());

        Vehicle saved = vehicleService.createVehicle(existing);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        boolean deleted = vehicleService.deleteVehicle(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
