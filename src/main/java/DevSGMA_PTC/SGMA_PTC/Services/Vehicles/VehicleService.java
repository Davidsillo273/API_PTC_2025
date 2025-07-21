package DevSGMA_PTC.SGMA_PTC.Services.Vehicles;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.Vehicle;
import DevSGMA_PTC.SGMA_PTC.Repositories.Vehicles.VehicleRepository;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        return vehicleRepository.findById(id)
                .map(v -> {
                    v.setPlateNumber(updatedVehicle.getPlateNumber());
                    v.setBrand(updatedVehicle.getBrand());
                    v.setModel(updatedVehicle.getModel());
                    v.setType(updatedVehicle.getType());
                    v.setColor(updatedVehicle.getColor());
                    v.setCirculationCardNumber(updatedVehicle.getCirculationCardNumber());
                    v.setVehicleImage(updatedVehicle.getVehicleImage());
                    return vehicleRepository.save(v);
                }).orElse(null);
    }

    public boolean deleteVehicle(Long id) {
        if(vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
