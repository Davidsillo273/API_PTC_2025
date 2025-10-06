package DevSGMA_PTC.SGMA_PTC.Services.Modules;

import DevSGMA_PTC.SGMA_PTC.Entities.Instructors.InstructorEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Levels.LevelEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleEntity;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Instructors.ExceptionInstructorNotFound;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Levels.ExceptionLevelNotFound;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Modules.ExceptionModuleNameDuplicated;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Modules.ExceptionModuleNotFound;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Instructors.InstructorInfoDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Modules.ModuleDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Instructors.InstructorRepository;
import DevSGMA_PTC.SGMA_PTC.Repositories.Levels.LevelRepository;
import DevSGMA_PTC.SGMA_PTC.Repositories.Modules.ModuleRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    // Obtener módulos paginados
    public Page<ModuleDTO> getAllModules(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ModuleEntity> pageResult = moduleRepository.findAll(pageable);
        return pageResult.map(this::convertToDTO);
    }


    // Insertar un módulo
    public ModuleDTO insertModule(@Valid ModuleDTO json) {

        LevelEntity level = levelRepository.findById(json.getLevelId())
                .orElseThrow(() -> new ExceptionLevelNotFound("Nivel no encontrado con ID: " + json.getLevelId()));

        if (verifyModuleExist(json.getModuleName())) {
            throw new ExceptionModuleNameDuplicated("El nombre del módulo ya está registrado en la base de datos");
        }

        InstructorEntity instructor = instructorRepository.findById(json.getInstructorId())
                .orElseThrow(() -> new ExceptionInstructorNotFound("Instructor no encontrado con ID: " + json.getInstructorId()));

        ModuleEntity entity = convertToEntity(json, instructor);
        ModuleEntity saved = moduleRepository.save(entity);
        return convertToDTO(saved);

    }

    // Actualizar un módulo
    public ModuleDTO updateModule(Long id, @Valid ModuleDTO json) {
        ModuleEntity moduleExist = moduleRepository.findById(id)
            .orElseThrow(() -> new ExceptionModuleNotFound("Modulo no encontrado."));

        moduleExist.setModuleName(json.getModuleName());
        moduleExist.setModuleCode(json.getModuleCode());

        LevelEntity level = levelRepository.findById(json.getLevelId())
                .orElseThrow(() -> new ExceptionLevelNotFound("Nivel no encontrado con ID: " + json.getLevelId()));
        moduleExist.setLevelId(level);

        InstructorEntity instructor = instructorRepository.findById(json.getInstructorId())
                .orElseThrow(() -> new ExceptionInstructorNotFound("Instructor no encontrado con ID: " + json.getInstructorId()));
        moduleExist.setInstructor(instructor);

        ModuleEntity moduleUpdated = moduleRepository.save(moduleExist);
        return convertToDTO(moduleUpdated);
    }

    // Eliminar un módulo
    public boolean deleteModule(Long id) {
        ModuleEntity module = moduleRepository.findById(id).orElse(null);
        if (module != null) {
            moduleRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public boolean verifyModuleExist(String moduleName) {

        return moduleRepository.existsByModuleName(moduleName);
    }


    // Métodos privados de conversión
    private ModuleDTO convertToDTO(ModuleEntity moduleEntity) {
        ModuleDTO dto = new ModuleDTO();
        dto.setModuleId(moduleEntity.getModuleId());
        dto.setModuleName(moduleEntity.getModuleName());
        dto.setModuleCode(moduleEntity.getModuleCode());

        if (moduleEntity.getLevelId() != null) {
            dto.setLevelName(moduleEntity.getLevelId().getLevelName());
            dto.setLevelId(moduleEntity.getLevelId().getLevelId());
        }

        // Instructor info para GET
        if (moduleEntity.getInstructor() != null) {
            InstructorInfoDTO instructorInfo = new InstructorInfoDTO();
            instructorInfo.setInstructorId(moduleEntity.getInstructor().getInstructorId());
            instructorInfo.setInstructorName(moduleEntity.getInstructor().getFirstName());
            dto.setInstructor(instructorInfo);
        }

        return dto;
    }

    // Sobrecarga para recibir InstructorEntity
    private ModuleEntity convertToEntity(@Valid ModuleDTO json, InstructorEntity instructor) {
        ModuleEntity entity = new ModuleEntity();
        entity.setModuleName(json.getModuleName());
        entity.setModuleCode(json.getModuleCode());

        LevelEntity levelEntity = levelRepository.findById(json.getLevelId())
                .orElseThrow(() -> new ExceptionLevelNotFound("ID del año académico del módulo no encontrado"));
        entity.setLevelId(levelEntity);

        entity.setInstructor(instructor);
        return entity;
    }
}
