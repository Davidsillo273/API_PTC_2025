package DevSGMA_PTC.SGMA_PTC.Services.Entries;


import DevSGMA_PTC.SGMA_PTC.Config.Security.Crypto.Argon2Password;
import DevSGMA_PTC.SGMA_PTC.Entities.Entries.EntryEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Students.StudentEntity;
import DevSGMA_PTC.SGMA_PTC.Exceptions.WorkOrders.ExceptionWorkOrdernotRegistred;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Entries.EntryDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Students.StudentDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Entries.EntryRepository;
import DevSGMA_PTC.SGMA_PTC.Repositories.WorkOrders.WorkOrderRepository;
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

    private EntryRepository entryRepository;
    private WorkOrderRepository workOrderRepository;

    public Page<EntryDTO> getAllEntries(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EntryEntity> pageEntity = entryRepository.findAll(pageable);
        return pageEntity.map(this::ConvertToDTO);
    }

    public EntryDTO createEntry(@Valid EntryDTO json) {
        if (json == null){
            throw new IllegalArgumentException("Es obligatorio enviar los datos del ingreso de un vehículo");
        }
        try{
            EntryEntity objData = ConvertToEntity(json);
            EntryEntity entrieSaved = entryRepository.save(objData);
            return ConvertToDTO(entrieSaved);
        }catch (Exception e){
            log.error("Error al registrar un ingreso de Vehiculo" + e.getMessage());
            throw new ExceptionWorkOrdernotRegistred("la inserción del ingreso de un nuevo vehículo no se insertó");
        }
    }


    // ----------- CONVERTIR A DTO --------------- ///
    private EntryDTO ConvertToDTO(EntryEntity entryEntity) {
        EntryDTO dto = new EntryDTO();
        dto.setEntryId(entryEntity.getEntryId());
        dto.setEntryDate(entryEntity.getEntryDate());
        // Asigna el nombre y ID del año académico si el estudiante tiene uno asociado
        if (entryEntity.getWorkOrderId() != null) {
            dto.setPlateNumber(entryEntity.getWorkOrderId().get);
            dto.setLevelId(studentEntity.getLevelId().getLevelId());
        }
        return dto;
    }

    // ----------- CONVERTIR A ENTITY --------------- ///
    private EntryEntity ConvertToEntity(@Valid EntryDTO json) {
        //creamo un objeto de la clase EntryEntity que se llamara Entity, el cual nos ayudara a convocar a nuestros set y get
        EntryEntity entity = new EntryEntity();
        //Se colocan todos los datos del entity
        entity.setEntryId(json.getEntryId());
        entity.setEntryDate(json.getEntryDate());
        entity.setWorkOrderId();json.get());
        //Convertimos a entity
        return entity;
    }

    private StudentEntity ConvertToEntity(@Valid StudentDTO json) {
        Argon2Password objHash = new Argon2Password();
        StudentEntity entity = new StudentEntity();
        entity.setFirstName(json.getFirstName());
        entity.setLastName(json.getLastName());
        entity.setEmail(json.getEmail());
        entity.setPassword(argon2.EncryptPassword(json.getPassword()));

        // Asigna el año académico si se proporciona un ID de año académico
        if (json.getLevelId() != null) {
            LevelEntity levelEntity = levelRepository.findById(json.getLevelId())
                    .orElseThrow(() -> new ExceptionLevelNotFound("ID del año académico del estudiante no encontrado"));
            entity.setLevelId(levelEntity);
        }
        return entity;
    }

}

