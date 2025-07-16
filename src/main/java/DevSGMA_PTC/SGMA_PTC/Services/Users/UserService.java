package DevSGMA_PTC.SGMA_PTC.Services.Users;

import DevSGMA_PTC.SGMA_PTC.Entities.Users.UserEntity;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Users.UserRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

// Capa de lógica de negocio. Aquí validamos antes de guardar o consultar datos
public class UserService {

    @Autowired
    UserRepository userRepository; // Repositorio que accede a la base de datos

    // Devuelve todos los usuarios
    public List<UserRequestDTO> getAllFromUsers() {
        List<UserEntity> User = userRepository.findAll();
        return User.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    public UserRequestDTO convertToUserDTO(UserEntity entity) {

        //Se crea un obj de tipo DTO, este será el obj a retornar
        UserRequestDTO dto = new UserRequestDTO();
        //Se transfieren los datos del Entity al DTO
        dto.setUserId(entity.getUserId());
        dto.setEmail(entity.getEmail());
        dto.setUsername(entity.getUsername());
        dto.setPasswordHash(entity.getPasswordHash());
        dto.setFullName(entity.getFullName());

        //Si en caso el valor proveniente de Entity es null se procede a guardar null en el DTO
        //En caso el cargo no sea Null, se guarda su ID
        if (entity.getRole() != null) {
            dto.setRoleName(entity.getRole().getRole());
            dto.setRoleId(entity.getRole().getRoleId());
        } else {
            dto.setRoleName("Sin rol asignado");
            dto.setRoleId(null);
        }
        return dto;
    }
}

//    // Busca un usuario por su ID
//    public User saveUser(User user) {
//        return userRepository.save(user);
//    }
//
//    // Guarda o actualiza un usuario
//    public Optional<User> getUserById(Long id) {
//        return userRepository.findById(id);
//    }
//
//    // Elimina un usuario por ID
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
