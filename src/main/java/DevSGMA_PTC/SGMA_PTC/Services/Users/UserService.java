package DevSGMA_PTC.SGMA_PTC.Services.Users;

import DevSGMA_PTC.SGMA_PTC.Repositories.Users.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Capa de lógica de negocio. Aquí validamos antes de guardar o consultar datos
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository; // Repositorio que accede a la base de datos

    // Devuelve todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Busca un usuario por su ID
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Guarda o actualiza un usuario
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Elimina un usuario por ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
