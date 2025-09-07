package DevSGMA_PTC.SGMA_PTC.Services.Auth.StudentsAuth;

import DevSGMA_PTC.SGMA_PTC.Config.Crypto.Argon2Password;
import DevSGMA_PTC.SGMA_PTC.Entities.Students.StudentEntity;
import DevSGMA_PTC.SGMA_PTC.Repositories.Students.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio de autenticación para estudiantes.
 * Proporciona métodos para validar credenciales y obtener información de estudiantes.
 */
@Service
public class StudentAuthenticationService {
    /**
     * Repositorio para acceder a los datos de los estudiantes.
     */
    @Autowired
    private StudentsRepository studentsRepository;

    /**
     * Verifica las credenciales de un estudiante usando Argon2.
     * Busca el estudiante por correo electrónico y compara la contraseña proporcionada con el hash almacenado.
     *
     * @param email Correo institucional del estudiante
     * @param password Contraseña en texto plano ingresada por el usuario
     * @return true si las credenciales son válidas, false en caso contrario
     */
    public boolean studentLogin(String email, String password) {
        Argon2Password objHash = new Argon2Password();
        Optional<StudentEntity> studentList = studentsRepository.findByEmail(email)
                .stream()
                .findFirst();
        if (studentList.isPresent()) {
            StudentEntity student = studentList.get();
            Long studentGrade = student.getGradeId().getGradeGroup(); // Grupo de grado del estudiante

            // Log de información básica del estudiante encontrado
            System.out.println("Estudiante encontrado, ID: " + student.getStudentId() +
                    ", correo: " + student.getEmail() +
                    ", grupo de grado: " + studentGrade);
            String HashDB = student.getPassword(); // Hash de la contraseña almacenada
            boolean verify = objHash.VerifyPassword(HashDB, password); // Verifica la contraseña
            return verify;
        }
        return false;
    }

    /**
     * Obtiene el estudiante completo por correo electrónico.
     *
     * @param email Correo institucional del estudiante
     * @return Optional con la entidad StudentEntity si existe, null si no se encuentra
     */
    public Optional<StudentEntity> getStudents(String email) {
        // Buscar estudiante completo en la base de datos
        Optional<StudentEntity> studentOpt = studentsRepository.findByEmail(email);
        return (studentOpt != null) ? studentOpt : null;
    }
}
