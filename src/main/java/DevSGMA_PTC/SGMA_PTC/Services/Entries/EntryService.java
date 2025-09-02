package DevSGMA_PTC.SGMA_PTC.Services.Entries;


import DevSGMA_PTC.SGMA_PTC.Entities.Entries.EntryEntity;
import DevSGMA_PTC.SGMA_PTC.Exceptions.WorkOrders.ExceptionWorkOrdernotRegistred;
import DevSGMA_PTC.SGMA_PTC.Exceptions.WorkOrders.ExceptionWorkOrdernotfound;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Entries.EntryDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Entries.EntryRepository;
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
public class EntryService {

    private EntryRepository repo;

    public Page<EntryDTO> getAllVehicleEntries(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EntryEntity> pageEntity = repo.findAll(pageable);
        return pageEntity.map(this::ConvertirADTO);
    }

    public EntryDTO insert(@Valid EntryDTO json) {
        if (json == null){
            throw new IllegalArgumentException("Para insertar una nueva Entrada de vehículo asegurate de ingresar todos los campos requeridos");
        }
        try{
            EntryEntity objData = ConvertirAEntity(json);
            EntryEntity VehicleEntrieSaved = repo.save(objData);
            return ConvertirADTO(VehicleEntrieSaved);
        }catch (Exception e){
            log.error("Error al registrar un ingreso de Vehiculo" + e.getMessage());
            throw new ExceptionWorkOrdernotRegistred("la inserción del ingreso de un nuevo vehículo no se insertó");
        }
    }





    // ----------- CONVERTIR A DTO --------------- ///
    private EntryDTO ConvertirADTO(EntryEntity entryEntity) {
        EntryDTO dto = new EntryDTO();
        dto.setEntryId(entryEntity.getEntryId());
        dto.setEntryTime(entryEntity.getEntryTime());
        dto.setOperationId(entryEntity.getOperationId());
        dto.setStatus(entryEntity.getStatus());
        return dto;
    }

    // ----------- CONVERTIR A ENTITY --------------- ///
    private EntryEntity ConvertirAEntity(@Valid EntryDTO json) {
        //creamo un objeto de la clase EntryEntity que se llamara Entity, el cual nos ayudara a convocar a nuestros set y get
        EntryEntity entity = new EntryEntity();
        //Se colocan todos los datos del entity
        entity.setEntryId(json.getEntryId());
        entity.setEntryTime(json.getEntryTime());
        entity.setOperationId(json.getOperationId());
        entity.setStatus(json.getStatus());
        //Convertimos a entity
        return entity;
    }

    public EntryDTO update(Long id, @Valid EntryDTO json) {
        EntryEntity vehicleEntriesExist = repo.findById(id).orElseThrow(()-> new ExceptionWorkOrdernotfound("Registro de ingreso de vehículos no encontrada"));
        vehicleEntriesExist.setEntryId(json.getEntryId());
        vehicleEntriesExist.setEntryTime(json.getEntryTime());
        vehicleEntriesExist.setOperationId(json.getOperationId());
        vehicleEntriesExist.setStatus(json.getStatus());
        EntryEntity VehicleEntrieUpdated = repo.save(vehicleEntriesExist);
        return ConvertirADTO(VehicleEntrieUpdated);
    }

    public boolean delete(Long id) {
        //1. Verificar la existencia del producto
        EntryEntity existence = repo.findById(id).orElse(null);
        //2. Si el valor existe lo elimina
        if (existence != null){
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}

