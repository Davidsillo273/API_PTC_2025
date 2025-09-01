package DevSGMA_PTC.SGMA_PTC.Services.Grades;

import DevSGMA_PTC.SGMA_PTC.Entities.Grades.GradeEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Levels.LevelEntity;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Grades.ExceptionGradeDontRegister;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Grades.ExceptionGradeNotFound;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Levels.ExceptionLevelNotFound;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Grades.GradeDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Grades.GradeRepository;
import DevSGMA_PTC.SGMA_PTC.Repositories.Levels.LevelRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GradeService {

    @Autowired
    private GradeRepository repo;

    @Autowired
    private LevelRepository levelRepo;

    // Obtener todos los grados
    public List<GradeDTO> getAllGrades() {
        return repo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Insertar un grado
    public GradeDTO insert(@Valid GradeDTO dto) {
        LevelEntity level = levelRepo.findById(dto.getLevelId())
                .orElseThrow(() -> new ExceptionLevelNotFound("Nivel no encontrado con ID: " + dto.getLevelId()));

        try {
            GradeEntity entity = convertToEntity(dto, level);
            GradeEntity saved = repo.save(entity);
            return convertToDTO(saved);
        } catch (Exception e) {
            log.error("Error al registrar el grado: " + e.getMessage());
            throw new ExceptionGradeDontRegister("No se pudo registrar el grado");
        }
    }

    // Actualizar un grado
    public GradeDTO update(Long id, @Valid GradeDTO dto) {
        GradeEntity grade = repo.findById(id)
                .orElseThrow(() -> new ExceptionGradeNotFound("Grado no encontrado con ID: " + id));

        LevelEntity level = levelRepo.findById(dto.getLevelId())
                .orElseThrow(() -> new ExceptionLevelNotFound("Nivel no encontrado con ID: " + dto.getLevelId()));

        grade.setGroupName(dto.getGroupName());
        grade.setLevel(level);

        try {
            GradeEntity updated = repo.save(grade);
            return convertToDTO(updated);
        } catch (Exception e) {
            log.error("Error al actualizar el grado: " + e.getMessage());
            throw new ExceptionGradeDontRegister("No se pudo actualizar el grado");
        }
    }

    // Eliminar un grado
    public boolean delete(Long id) {
        GradeEntity grade = repo.findById(id).orElse(null);
        if (grade != null) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    // Métodos privados de conversión
    private GradeDTO convertToDTO(GradeEntity entity) {
        GradeDTO dto = new GradeDTO();
        dto.setId(entity.getId());
        dto.setGroupName(entity.getGroupName());
        dto.setLevelId(entity.getLevel().getId());
        return dto;
    }

    private GradeEntity convertToEntity(GradeDTO dto, LevelEntity level) {
        GradeEntity entity = new GradeEntity();
        entity.setGroupName(dto.getGroupName());
        entity.setLevel(level);
        return entity;
    }
}
