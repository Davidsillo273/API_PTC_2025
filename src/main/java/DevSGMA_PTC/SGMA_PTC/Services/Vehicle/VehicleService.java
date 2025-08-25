package DevSGMA_PTC.SGMA_PTC.Services.Vehicle;

import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.VehicleEntity;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Vehicles.VehiclesDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Vehicles.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepo vehicleRepo;


    private VehiclesDTO convertToDTO(VehicleEntity entity) {
        VehiclesDTO dto = new VehiclesDTO();
        dto.setId(entity.getId());
        dto.setPlateNumber(entity.getPlateNumber());
        dto.setBrand(entity.getBrand());
        dto.setModel(entity.getModel());
        dto.setColor(entity.getColor());
        dto.setCirculationCardNumber(entity.getCirculationCardNumber());
        dto.setVehicleImage(entity.getVehicleImage());
        return dto;
    }


    private VehicleEntity convertToEntity(VehiclesDTO dto) {
        VehicleEntity entity = new VehicleEntity();
        entity.setId(dto.getId());
        entity.setPlateNumber(dto.getPlateNumber());
        entity.setBrand(dto.getBrand());
        entity.setModel(dto.getModel());
        entity.setColor(dto.getColor());
        entity.setCirculationCardNumber(dto.getCirculationCardNumber());
        entity.setVehicleImage(dto.getVehicleImage());
        return entity;
    }


    public VehiclesDTO save(VehiclesDTO dto) {
        VehicleEntity entity = convertToEntity(dto);
        return convertToDTO(vehicleRepo.save(entity));
    }


    public List<VehiclesDTO> getAll() {
        return vehicleRepo.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public Optional<VehiclesDTO> getById(Long id) {
        return vehicleRepo.findById(id).map(this::convertToDTO);
    }


    public Optional<VehiclesDTO> getByPlate(String plateNumber) {
        return vehicleRepo.findByPlateNumber(plateNumber).map(this::convertToDTO);
    }


    public void delete(Long id) {
        vehicleRepo.deleteById(id);
    }
}