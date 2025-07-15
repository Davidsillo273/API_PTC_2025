package DevSGMA_PTC.SGMA_PTC.Services.VehiclesEntrys;

import DevSGMA_PTC.SGMA_PTC.Entities.VehiclesEntrys.VehicleEntryEntity;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Vehicles.VehicleRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.VehiclesEntrys.VehicleEntryRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.VehiclesEntrys.VehicleEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class VehicleEntryService {

    @Autowired
    VehicleEntryRepository VehicleRepository;

    // Devuelve todos los roles
    public List<VehicleEntryRequestDTO> VehicleEntries () {
        List<VehicleEntryEntity>Vehicles = VehicleRepository.findAll();
        return Vehicles.stream()
                .map(this::convertVehiclesDTO)
                .collect(Collectors.toList());

    }

    public VehicleEntryRequestDTO convertVehiclesDTO(VehicleEntryEntity entity){
        VehicleEntryRequestDTO dto = new VehicleEntryRequestDTO();
        dto.setBrand(entity.getBrand());
        dto.setModel(entity.getModel());
        dto.setNotes(entity.getNotes());
        dto.setLicensePlate(entity.getLicensePlate());
        return dto;
    }
}



//    // Devuelve un rol específico por ID
//    public Optional<Role> getRoleById(Long id) {
//        return VehicleEntryRepository.findById(id);
//    }
//
//    // Guarda o actualiza un rol
//    public Role saveRole(Role role) {
//        return VehicleEntryRepository.save(role);
//    }
//
//    // Elimina un rol por ID
//    public void deleteRole(Long id) {
//        VehicleEntryRepository.deleteById(id);
//    }

