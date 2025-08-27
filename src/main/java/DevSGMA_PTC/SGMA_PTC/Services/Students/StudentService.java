package DevSGMA_PTC.SGMA_PTC.Services.Students;

import DevSGMA_PTC.SGMA_PTC.Config.Security.Crypto.Argon2Password;
import DevSGMA_PTC.SGMA_PTC.Entities.Students.StudentEntity;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Roles.ExceptionRoleNotFound;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Students.ExceptionEmailStudentDuplicate;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Students.ExceptionStudentNotFound;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Students.StudentDTO;
import DevSGMA_PTC.SGMA_PTC.Utils.PasswordGenerator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import DevSGMA_PTC.SGMA_PTC.Repositories.Students.StudentsRepository;

@Slf4j // Anotación de Lombok para logging
@Service // Anotación de Spring para marcar esta clase como un servicio
public class StudentService {

    @Autowired
    private LevelRepository levelRepository; // Repositorio que accede a los años académicos de los estudiantes
    @Autowired
    private StudentsRepository studentsRepository; // Repositorio que accede a la base de datos
    @Autowired
    private Argon2Password argon2; // Servicio de encriptación de contraseñas


    //*** MÉTODO PARA OBTENER TODOS LOS ESTUDIANTES ***\\

    /**
     * Obtiene todos los estudiantes paginados y los convierte a DTO.
     *
     * @param page Número de página a consultar.
     * @param size Tamaño de la página (cantidad de elementos por página).
     * @return Página de usuarios en formato DTO.
     */
    public Page<StudentDTO> getAllStudents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentEntity> studentEntityPage = studentsRepository.findAll(pageable);

        return studentEntityPage.map(this::ConvertToDTO);
    }

    //*** MÉTODO PARA CREAR UN NUEVO ESTUDIANTE ***\\

    /**
     * Crea un nuevo usuario a partir de un DTO validado.
     *
     * @param json Objeto StudentDTO con los datos del estudiante.
     * @return Objeto StudentDTO del estudiante creado.
     * @throws ExceptionEmailStudentDuplicate si el correo ya existe.
     */
    public StudentDTO createStudent(@Valid StudentDTO json) {
        if (verifyStudentExist(json.getEmail())) {
            throw new ExceptionEmailStudentDuplicate("El correo ya está registrado en la base de datos");
        }
        StudentEntity objEntity = ConvertToEntity(json);
        StudentEntity saveStudent = studentsRepository.save(objEntity);

        return ConvertToDTO(saveStudent);
    }

    //*** MÉTODO PARA ACTUALIZAR UN ESTUDIANTE EXISTENTE ***\\

    /**
     * Actualiza los datos de un estudiante existente.
     *
     * @param id   ID del estudiante a actualizar.
     * @param json Objeto StudentDTO con los nuevos datos.
     * @return Objeto StudentDTO actualizado.
     * @throws ExceptionStudentNotFound       si el estudiante no existe.
     * @throws ExceptionEmailStudentDuplicate si el nuevo correo ya está registrado.
     * @throws ExceptionRoleNotFound       si el año académico proporcionado no existe.
     */
    public StudentDTO updateStudent(@Valid Long id, StudentDTO json) {

        //Se verifica la existencia
        StudentEntity exist = studentsRepository.findById(id).orElseThrow(() ->
                new ExceptionStudentNotFound("Estudiante no encontrado"));
        if (!exist.getEmail().equals(json.getEmail())) {
            if (verifyStudentExist(json.getEmail())) {
                throw new ExceptionEmailStudentDuplicate("El correo que se quiere registrar ya existe en la base de datos");
            }
        }
        //Actualizar valores
        exist.setFirstName(json.getFirstName());
        exist.setLastName(json.getLastName());
        exist.setEmail(json.getEmail());

        // Solo actualiza la contraseña si se proporciona una nueva
        if (json.getPassword() != null && !json.getPassword().isEmpty()) {
            exist.setPassword(argon2.EncryptPassword(json.getPassword()));
        }

        // Actualizar el año académico si se proporciona un nuevo ID de rol
        if (json.getLevelId() != null) {
            LevelEntity levelEntity = levelRepository.findById(json.getLevelId())
                    .orElseThrow(() -> new ExceptionRoleNotFound("ID del año académico del estudiante no encontrado"));
            exist.setStudentId(levelEntity);
        }
        StudentEntity studentUpdated = studentsRepository.save(exist);
        return ConvertToDTO(studentUpdated);
    }

    //*** MÉTODO PARA ELIMINAR UN USUARIO ***\\

    /**
     * Elimina un usuario si existe en la base de datos.
     *
     * @param id ID del usuario a eliminar.
     * @return true si se eliminó exitosamente, false si no se encontró.
     */
    public boolean deleteStudent(Long id) {
        StudentEntity exist = studentsRepository.findById(id).orElse(null);
        if (exist != null) {
            studentsRepository.deleteById(id);
            return true;
        } else {
            log.error("Estudiante no encontrado");
            return false;
        }
    }

    //*** MÉTODO PARA RESETEAR LA CONTRASEÑA DE UN USUARIO ***\\

    /**
     *
     * @param id ID del usuario cuya contraseña se va a resetear.
     * @return true si la contraseña se reseteó exitosamente, false si el usuario no fue encontrado. False si el usuario no fue encontrado.
     * @throws ExceptionStudentNotFound si el usuario no existe.
     */
    public boolean resetStudentPassword(@Valid Long id) {
        StudentEntity existing = studentsRepository.findById(id).orElseThrow(() -> new ExceptionStudentNotFound("Estudiante no encontrado"));
        if (existing != null) {
            String newPassword = PasswordGenerator.generateSecurePassword(12);
            existing.setPassword(argon2.EncryptPassword(newPassword));
            StudentEntity studentUpdated = studentsRepository.save(existing);
            return true;
        }
        return false;
    }

//*** MÉTODOS COMPLEMENTARIOS***\\


    /**
     * Verifica si un estudiante ya existe en la base de datos por su correo institucional.
     *
     * @param email Correo institucional del estudiante.
     * @return true si el correo ya existe, false si no.
     */
    public boolean verifyStudentExist(String email) {

        return studentsRepository.existsByEmail(email);
    }

    /**
     * Convierte una entidad de usuario (StudentEntity) a un DTO (StudentDTO).
     *
     * @param studentEntity Entidad del usuario a convertir.
     * @return Objeto StudentDTO con los datos convertidos.
     */
    private StudentDTO ConvertToDTO(StudentEntity studentEntity) {
        StudentDTO dto = new StudentDTO();
        dto.setStudentId(studentEntity.getStudentId());
        dto.setFirstName(studentEntity.getFirstName());
        dto.setLastName(studentEntity.getLastName());
        dto.setEmail(studentEntity.getEmail());
        dto.setPassword(studentEntity.getPassword());

        // Asigna el nombre y ID del año académico si el estudiante tiene uno asociado
        if (studentEntity.getLevelId() != null) {
            dto.setLevelName(studentEntity.getLevelId().getLevelName());
            dto.setLevelId(studentEntity.getLevelId().getLevelId());
        }

        return dto;
    }

    /**
     * Convierte un objeto DTO (StudentDTO) en una entidad de usuario (StudentEntity).
     *
     * @param json Objeto StudentDTO con los datos del usuario.
     * @return Objeto StudentEntity con los datos listos para guardar en la base de datos.
     * @throws ExceptionRoleNotFound si el ID del rol no existe en la base.
     */
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
                    .orElseThrow(() -> new ExceptionRoleNotFound("ID del año académico del estudiante no encontrado"));
            entity.setLevelId(levelEntity);
        }
        return entity;
    }

}
