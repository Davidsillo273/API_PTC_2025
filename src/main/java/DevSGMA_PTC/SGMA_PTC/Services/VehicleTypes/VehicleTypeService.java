package DevSGMA_PTC.SGMA_PTC.Services.VehicleTypes;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import DevSGMA_PTC.SGMA_PTC.Entities.VehicleTypes.VehicleType;
import DevSGMA_PTC.SGMA_PTC.Repositories.VehicleTypes.VehicleTypeRepository;

@Service
public class VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;

    public VehicleTypeService(VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    public List<VehicleType> getAllVehicleTypes() {
        return vehicleTypeRepository.findAll();
    }

    public Optional<VehicleType> getVehicleTypeById(Long id) {
        return vehicleTypeRepository.findById(id);
    }

    public VehicleType createVehicleType(VehicleType vehicleType) {
        return vehicleTypeRepository.save(vehicleType);
    }

    public VehicleType updateVehicleType(Long id, VehicleType updatedVehicleType) {
        return vehicleTypeRepository.findById(id)
                .map(vt -> {
                    vt.setName(updatedVehicleType.getName());
                    return vehicleTypeRepository.save(vt);
                }).orElse(null);
    }

    public boolean deleteVehicleType(Long id) {
        if(vehicleTypeRepository.existsById(id)) {
            vehicleTypeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
