package DevSGMA_PTC.SGMA_PTC.Services.Vehicle;

import DevSGMA_PTC.SGMA_PTC.Entities.Students.StudentEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.VehicleEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.VehiclesTypes.VehicleTypeEntity;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Students.ExceptionStudentNotFound;
import DevSGMA_PTC.SGMA_PTC.Exceptions.VehicleType.ExceptionVehicleTypeIdNotFound;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Vehicles.ExceptionPlateNumberDuplicated;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Vehicles.VehicleDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Students.StudentsRepository;
import DevSGMA_PTC.SGMA_PTC.Repositories.VehicleType.VehicleTypeRepository;
import DevSGMA_PTC.SGMA_PTC.Repositories.Vehicles.VehicleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private StudentsRepository studentsRepository;

    //*** MÉTODOS PRINCIPALES ***\\

    // Método para obtener todos los vehículos con paginación
    public Page<VehicleDTO> getAllVehicles(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<VehicleEntity> vehicleEntityPage = vehicleRepository.findAll(pageable);

        return vehicleEntityPage.map(this::convertToDTO);
    }

    // Métodos para obtener vehículos por diferentes criterios
    public List<VehicleDTO> getByPlateNumber(String plateNumber) {
        return vehicleRepository.findByPlateNumber(plateNumber)
                .map(this::convertToDTO) // convierte si existe
                .map(List::of)           // lo mete en una lista con un solo elemento
                .orElseGet(List::of);    // si no existe, devuelve lista vacía
    }

    public List<VehicleDTO> getByCirculationCardNumber(String circulationCardNumber) {
        return vehicleRepository.findByPlateNumber(circulationCardNumber)
                .map(this::convertToDTO) // convierte si existe
                .map(List::of)           // lo mete en una lista con un solo elemento
                .orElseGet(List::of);    // si no existe, devuelve lista vacía
    }

    public List<VehicleDTO>  getByOwnerPhone(String ownerPhone) {
        return vehicleRepository.findByPlateNumber(ownerPhone)
                .map(this::convertToDTO) // convierte si existe
                .map(List::of)           // lo mete en una lista con un solo elemento
                .orElseGet(List::of);    // si no existe, devuelve lista vacía
    }

    // Método para crear un nuevo vehículo
    public VehicleDTO createVehicle(@Valid VehicleDTO json) {
        if (verifyVehicleExist(json.getPlateNumber())) {
            throw new ExceptionPlateNumberDuplicated("El número de placa ya existe en el sistema");
        }

        VehicleEntity objEntity = convertToEntity(json);
        VehicleEntity saveVehicle = vehicleRepository.save(objEntity);

        return convertToDTO(saveVehicle);
    }


    //*** MÉTODOS COMPLEMENTARIOS***\\

    public boolean verifyVehicleExist(String plateNumber) {

        return vehicleRepository.existsByPlateNumber(plateNumber);
    }

    private VehicleDTO convertToDTO(VehicleEntity entity) {
        VehicleDTO dto = new VehicleDTO();
        dto.setVehicleId(entity.getVehicleId());
        dto.setPlateNumber(entity.getPlateNumber());
        dto.setHasPolicy(entity.getHasPolicy());
        dto.setPolicyNumber(entity.getPolicyNumber());
        dto.setBrand(entity.getBrand());
        dto.setModel(entity.getModel());

        // Asigna el nombre y ID del tipo de vehículo si el vehículo tiene uno asociado
        if (entity.getTypeId() != null) {
            dto.setTypeName(entity.getTypeId().getTypeName());
            dto.setTypeId(entity.getTypeId().getTypeId());
        }

        dto.setColor(entity.getColor());
        dto.setCirculationCardNumber(entity.getCirculationCardNumber());
        dto.setOwnerName(entity.getOwnerName());
        dto.setOwnerDui(entity.getOwnerDui());
        dto.setOwnerPhone(entity.getOwnerPhone());
        dto.setVehicleImage(entity.getVehicleImage());

        // Asigna el nombre y ID del estudiante si el vehículo está asociado a un estudiante
        if (entity.getStudentId() != null) {
            dto.setStudentName(entity.getStudentId().getLastName());
            dto.setStudentLastName(entity.getStudentId().getLastName());
            dto.setStudentId(entity.getStudentId().getStudentId());
        }

        dto.setMaintenanceEXPO(entity.getMaintenanceExpo());
        dto.setIdStatus(entity.getStatus());

        return dto;
    }

    private VehicleEntity convertToEntity(@Valid VehicleDTO json) {
        VehicleEntity entity = new VehicleEntity();

        entity.setPlateNumber(json.getPlateNumber());
        entity.setHasPolicy(json.getHasPolicy());
        entity.setPolicyNumber(json.getPolicyNumber());
        entity.setBrand(json.getBrand());
        entity.setModel(json.getModel());

        // Asigna el año académico si se proporciona un ID de año académico
        if (json.getTypeId() != null) {
            VehicleTypeEntity vehicleTypeEntity = vehicleTypeRepository.findById(json.getTypeId())
                    .orElseThrow(() -> new ExceptionVehicleTypeIdNotFound("ID del tipo de vehículo no encontrado"));
            entity.setTypeId(vehicleTypeEntity);
        }

        entity.setColor(json.getColor());
        entity.setCirculationCardNumber(json.getCirculationCardNumber());
        entity.setOwnerName(json.getOwnerName());
        entity.setOwnerDui(json.getOwnerDui());
        entity.setOwnerPhone(json.getOwnerPhone());
        entity.setVehicleImage(json.getVehicleImage());

        if (json.getStudentId() != null) {
            StudentEntity studentEntity = studentsRepository.findById(json.getStudentId())
                    .orElseThrow(() -> new ExceptionStudentNotFound("ID del estudiante no encontrado " + json.getStudentId()));
            entity.setStudentId(studentEntity);
        }

        entity.setMaintenanceExpo(json.getMaintenanceEXPO());
        entity.setStatus(json.getIdStatus());

        return entity;
    }

}