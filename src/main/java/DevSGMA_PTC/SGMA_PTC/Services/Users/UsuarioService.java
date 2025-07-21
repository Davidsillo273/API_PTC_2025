package DevSGMA_PTC.SGMA_PTC.Services.Users;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import DevSGMA_PTC.SGMA_PTC.Entities.Users.Usuario;
import DevSGMA_PTC.SGMA_PTC.Repositories.Users.UserRepository;

@Service
public class UsuarioService {

    private final UserRepository userRepository;

    public UsuarioService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Usuario> getAllUsuarios() {
        return userRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return userRepository.findById(id);
    }

    public Usuario createUsuario(Usuario usuario) {
        return userRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario updatedUsuario) {
        return userRepository.findById(id)
                .map(usuario -> {
                    usuario.setUsername(updatedUsuario.getUsername());
                    usuario.setLastName(updatedUsuario.getLastName());
                    usuario.setInstiEmail(updatedUsuario.getInstiEmail());
                    usuario.setPassword(updatedUsuario.getPassword());
                    usuario.setTeacherGrade(updatedUsuario.getTeacherGrade());
                    usuario.setRole(updatedUsuario.getRole());
                    return userRepository.save(usuario);
                }).orElse(null);
    }

    public boolean deleteUsuario(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
