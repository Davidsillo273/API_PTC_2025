package DevSGMA_PTC.SGMA_PTC.Services.Vehicles;


import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.VehicleEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.VehiclesTypes.vehicleTypeEntity;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Vehicles.VehicleRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Vehicles.VehicleResponseDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Vehicles.VehicleRepository;
import DevSGMA_PTC.SGMA_PTC.Repositories.VehiclesTypes.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repo;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    public List<VehicleResponseDTO> obtenerTodo() {

        List<VehicleEntity> Vehicle = repo.findAll();
        return Vehicle.stream()
                .map(this::convertirVehicleDTO)
                .collect(Collectors.toList());
    }

    public VehicleResponseDTO convertirVehicleDTO(VehicleEntity entity){

        VehicleResponseDTO dto = new VehicleResponseDTO();
        dto.setVehicleId(entity.getVehicleId());
        dto.setPlateNumber(entity.getPlateNumber());
        dto.setBrand(entity.getBrand());
        dto.setModel(entity.getModel());
        dto.setTypeId(entity.getTypeId());
        dto.setColor(entity.getColor());
        dto.setCirculationCardNumber(entity.getCirculationNumber());
        //falta imagen de vehiculo
        return dto;
    }


}
