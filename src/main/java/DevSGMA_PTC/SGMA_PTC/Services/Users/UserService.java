package DevSGMA_PTC.SGMA_PTC.Services.Users;

import DevSGMA_PTC.SGMA_PTC.Entities.Users.UserEntity;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Users.UserRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Users.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Capa de lógica de negocio. Aquí validamos antes de guardar o consultar datos
public class UserService {

    @Autowired
    private UserRepository userRepository; // Repositorio que accede a la base de datos

    // Devuelve todos los usuarios
    public List<UserRequestDTO> getAllUsers() {
        List<UserEntity> User = userRepository.findAll();
        return User.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    public UserRequestDTO convertToUserDTO(UserEntity entity){

        //Este será el obj a retornar
        UserRequestDTO dto = new UserRequestDTO();
        dto.setUserId(entity.getUserId());
        dto.setEmail(entity.getEmail());
        dto.setUsername(entity.getUsername());
        dto.setFullName(entity.getFullName());
        dto.setPasswordHash(entity.getPasswordHash());
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
