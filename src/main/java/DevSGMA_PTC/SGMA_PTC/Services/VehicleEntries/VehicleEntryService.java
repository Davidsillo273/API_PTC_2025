package DevSGMA_PTC.SGMA_PTC.Services.VehicleEntries;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import DevSGMA_PTC.SGMA_PTC.Entities.VehicleEntries.VehicleEntry;
import DevSGMA_PTC.SGMA_PTC.Repositories.VehicleEntries.VehicleEntryRepository;

@Service
public class VehicleEntryService {

    private final VehicleEntryRepository vehicleEntryRepository;

    public VehicleEntryService(VehicleEntryRepository vehicleEntryRepository) {
        this.vehicleEntryRepository = vehicleEntryRepository;
    }

    public List<VehicleEntry> getAllEntries() {
        return vehicleEntryRepository.findAll();
    }

    public Optional<VehicleEntry> getEntryById(Long id) {
        return vehicleEntryRepository.findById(id);
    }

    public VehicleEntry createEntry(VehicleEntry entry) {
        return vehicleEntryRepository.save(entry);
    }

    public VehicleEntry updateEntry(Long id, VehicleEntry updatedEntry) {
        return vehicleEntryRepository.findById(id)
                .map(e -> {
                    e.setVehicle(updatedEntry.getVehicle());
                    e.setEntryTime(updatedEntry.getEntryTime());
                    e.setOperation(updatedEntry.getOperation());
                    e.setStatus(updatedEntry.getStatus());
                    return vehicleEntryRepository.save(e);
                }).orElse(null);
    }

    public boolean deleteEntry(Long id) {
        if(vehicleEntryRepository.existsById(id)) {
            vehicleEntryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
