package DevSGMA_PTC.SGMA_PTC.Services.VehiclesEntrys;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.VehiclesEntrys.VehicleEntryRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.VehiclesEntrys.VehicleEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class VehicleEntryService {

//    @Autowired
//    private VehicleEntryRepository vehicleEntryRepository;
//
//    @Override
//    public List<VehicleEntry> getAllVehicleEntries() {
//        return vehicleEntryRepository.findAll();
//    }
//
//    @Override
//    public VehicleEntry getVehicleEntryById(Long id) {
//        return vehicleEntryRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Entrada de vehículo no encontrada"));
//    }
//
//    @Override
//    public VehicleEntry createVehicleEntry(VehicleEntry vehicleEntry) {
//        vehicleEntry.setEntryDate(LocalDateTime.now());
//        return vehicleEntryRepository.save(vehicleEntry);
//    }
//
//    // Método adicional para crear desde DTO
//    public VehicleEntry createFromDTO(VehicleEntryRequestDTO dto) {
//        VehicleEntry entry = new VehicleEntry();
//        entry.setLicensePlate(dto.getLicensePlate());
//        entry.setBrand(dto.getBrand());
//        entry.setModel(dto.getModel());
//        entry.setNotes(dto.getNotes());
//        return createVehicleEntry(entry);
//    }
//
//    @Override
//    public VehicleEntry updateVehicleEntry(Long id, VehicleEntry vehicleEntry) {
//        VehicleEntry existingEntry = getVehicleEntryById(id);
//        existingEntry.setLicensePlate(vehicleEntry.getLicensePlate());
//        existingEntry.setBrand(vehicleEntry.getBrand());
//        existingEntry.setModel(vehicleEntry.getModel());
//        existingEntry.setNotes(vehicleEntry.getNotes());
//        return vehicleEntryRepository.save(existingEntry);
//    }
//
//    @Override
//    public void deleteVehicleEntry(Long id) {
//        vehicleEntryRepository.deleteById(id);
   // }
}
