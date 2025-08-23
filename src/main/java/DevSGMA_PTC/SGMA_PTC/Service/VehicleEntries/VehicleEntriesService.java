package DevSGMA_PTC.SGMA_PTC.Service.VehicleEntries;


import DevSGMA_PTC.SGMA_PTC.Entities.VehicleEntries.VehicleEntriesEntity;
import DevSGMA_PTC.SGMA_PTC.Exceptions.WorkOrders.ExceptionWorkOrdernotRegistred;
import DevSGMA_PTC.SGMA_PTC.Exceptions.WorkOrders.ExceptionWorkOrdernotfound;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.VehicleEntries.VehicleEntriesDTO;
import DevSGMA_PTC.SGMA_PTC.Repository.VehicleEntries.VehicleEntriesRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Slf4j
@CrossOrigin
@Service
public class VehicleEntriesService {

    private VehicleEntriesRepository repo;

    public Page<VehicleEntriesDTO> getAllVehicleEntries(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<VehicleEntriesEntity> pageEntity = repo.findAll(pageable);
        return pageEntity.map(this::ConvertirADTO);
    }




    public VehicleEntriesDTO insert(@Valid VehicleEntriesDTO json) {
        if (json == null){
            throw new IllegalArgumentException("Para insertar una nueva Entrada de vehículo asegurate de ingresar todos los campos requeridos");
        }
        try{
            VehicleEntriesEntity objData = ConvertirAEntity(json);
            VehicleEntriesEntity VehicleEntrieSaved = repo.save(objData);
            return ConvertirADTO(VehicleEntrieSaved);
        }catch (Exception e){
            log.error("Error al registrar un ingreso de Vehiculo" + e.getMessage());
            throw new ExceptionWorkOrdernotRegistred("la inserción del ingreso de un nuevo vehículo no se insertó");
        }
    }





    // ----------- CONVERTIR A DTO --------------- ///
    private VehicleEntriesDTO ConvertirADTO(VehicleEntriesEntity vehicleEntriesEntity) {
        VehicleEntriesDTO dto = new VehicleEntriesDTO();
        dto.setEntryId(vehicleEntriesEntity.getEntryId());
        dto.setEntryTime(vehicleEntriesEntity.getEntryTime());
        dto.setOperationId(vehicleEntriesEntity.getOperationId());
        dto.setStatus(vehicleEntriesEntity.getStatus());
        return dto;
    }

    // ----------- CONVERTIR A ENTITY --------------- ///
    private VehicleEntriesEntity ConvertirAEntity(@Valid VehicleEntriesDTO json) {
        //creamo un objeto de la clase VehicleEntriesEntity que se llamara Entity, el cual nos ayudara a convocar a nuestros set y get
        VehicleEntriesEntity entity = new VehicleEntriesEntity();
        //Se colocan todos los datos del entity
        entity.setEntryId(json.getEntryId());
        entity.setEntryTime(json.getEntryTime());
        entity.setOperationId(json.getOperationId());
        entity.setStatus(json.getStatus());
        //Convertimos a entity
        return entity;
    }

    public VehicleEntriesDTO update(Long id, @Valid VehicleEntriesDTO json) {
        VehicleEntriesEntity vehicleEntriesExist = repo.findById(id).orElseThrow(()-> new ExceptionWorkOrdernotfound("Registro de ingreso de vehículos no encontrada"));
        vehicleEntriesExist.setEntryId(json.getEntryId());
        vehicleEntriesExist.setEntryTime(json.getEntryTime());
        vehicleEntriesExist.setOperationId(json.getOperationId());
        vehicleEntriesExist.setStatus(json.getStatus());
        VehicleEntriesEntity VehicleEntrieUpdated = repo.save(vehicleEntriesExist);
        return ConvertirADTO(VehicleEntrieUpdated);
    }

    public boolean delete(Long id) {
        //1. Verificar la existencia del producto
        VehicleEntriesEntity existence = repo.findById(id).orElse(null);
        //2. Si el valor existe lo elimina
        if (existence != null){
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}

