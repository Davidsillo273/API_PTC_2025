package DevSGMA_PTC.SGMA_PTC.Services.Users;

import DevSGMA_PTC.SGMA_PTC.Config.Security.Crypto.Argon2Password;
import DevSGMA_PTC.SGMA_PTC.Entities.Roles.RoleEntity;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Roles.RoleNotFoundException;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Users.EmailUserDuplicateException;
import DevSGMA_PTC.SGMA_PTC.Exceptions.Users.UserNotFoundException;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Users.UserDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Roles.RoleRepository;
import DevSGMA_PTC.SGMA_PTC.Utils.PasswordGenerator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import DevSGMA_PTC.SGMA_PTC.Entities.Users.UserEntity;
import DevSGMA_PTC.SGMA_PTC.Repositories.Users.UserRepository;

@Slf4j // Anotación de Lombok para logging
@Service // Anotación de Spring para marcar esta clase como un servicio
public class UserService {

    @Autowired
    private RoleRepository roleRepository; // Repositorio que accede a los roles de usuario
    @Autowired
    private UserRepository userRepository; // Repositorio que accede a la base de datos
    @Autowired
    private Argon2Password argon2; // Servicio de encriptación de contraseñas


    //*** MÉTODO PARA OBTENER TODOS LOS USUARIOS ***\\

    /**
     * Obtiene todos los usuarios paginados y los convierte a DTO.
     *
     * @param page Número de página a consultar.
     * @param size Tamaño de la página (cantidad de elementos por página).
     * @return Página de usuarios en formato DTO.
     */
    public Page<UserDTO> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> userEntityPage = userRepository.findAll(pageable);
        return userEntityPage.map(this::ConvertToDTO);
    }

    //*** MÉTODO PARA CREAR UN NUEVO USUARIO ***\\

    /**
     * Crea un nuevo usuario a partir de un DTO validado.
     *
     * @param json Objeto UserDTO con los datos del usuario.
     * @return Objeto UserDTO del usuario creado.
     * @throws EmailUserDuplicateException si el correo ya existe.
     */
    public UserDTO createUser(@Valid UserDTO json) {
        if (verifyUserExist(json.getInstiEmail())) {
            throw new EmailUserDuplicateException("El correo ya esta registrado en la base de datos");
        }
        UserEntity objEntity = ConvertToEntity(json);
        UserEntity savedUser = userRepository.save(objEntity);
        return ConvertToDTO(savedUser);
    }

    //*** MÉTODO PARA ACTUALIZAR UN USUARIO EXISTENTE ***\\

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param id   ID del usuario a actualizar.
     * @param json Objeto UserDTO con los nuevos datos.
     * @return Objeto UserDTO actualizado.
     * @throws UserNotFoundException       si el usuario no existe.
     * @throws EmailUserDuplicateException si el nuevo correo ya está registrado.
     * @throws RoleNotFoundException       si el rol proporcionado no existe.
     */
    public UserDTO updateUser(@Valid Long id, UserDTO json) {
        //Se verifica la existencia
        UserEntity exist = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("Usuario no encontrado"));
        if (!exist.getInstiEmail().equals(json.getInstiEmail())) {
            if (verifyUserExist(json.getInstiEmail())) {
                throw new EmailUserDuplicateException("El coreo que se quiere registrar ya existe en la base de datos");
            }
        }
        //Actualizar valores
        exist.setUserName(json.getUserName());
        exist.setLastName(json.getLastName());
        exist.setInstiEmail(json.getInstiEmail());

        // Solo actualiza la contraseña si se proporciona una nueva
        if (json.getPassword() != null && !json.getPassword().isEmpty()) {
            exist.setPassword(argon2.EncryptPassword(json.getPassword()));
        }

        exist.setGrade(json.getGrade());
        exist.setImagenUrl(json.getImagenUrl());

        // Actualizar el rol si se proporciona un nuevo ID de rol
        if (json.getRoleId() != null) {
            RoleEntity entityRole = roleRepository.findById(json.getRoleId())
                    .orElseThrow(() -> new RoleNotFoundException("ID del rol del usuario no encontrado"));
            exist.setRoleId(entityRole);
        }
        UserEntity userUpdated = userRepository.save(exist);
        return ConvertToDTO(userUpdated);
    }

    //*** MÉTODO PARA ELIMINAR UN USUARIO ***\\

    /**
     * Elimina un usuario si existe en la base de datos.
     *
     * @param id ID del usuario a eliminar.
     * @return true si se eliminó exitosamente, false si no se encontró.
     */
    public boolean deleteUser(Long id) {
        UserEntity exist = userRepository.findById(id).orElse(null);
        if (exist != null) {
            userRepository.deleteById(id);
            return true;
        } else {
            log.error("Usuario no encontrado");
            return false;
        }
    }

    //*** MÉTODO PARA RESETEAR LA CONTRASEÑA DE UN USUARIO ***\\

    /**
     *
     * @param id ID del usuario cuya contraseña se va a resetear.
     * @return true si la contraseña se reseteó exitosamente, false si el usuario no fue encontrado. False si el usuario no fue encontrado.
     * @throws UserNotFoundException si el usuario no existe.
     */
    public boolean resetPassword(@Valid Long id) {
        UserEntity existing = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
        if (existing != null) {
            String newPassword = PasswordGenerator.generateSecurePassword(12);
            existing.setPassword(argon2.EncryptPassword(newPassword));
            UserEntity userUpdated = userRepository.save(existing);
            return true;
        }
        return false;
    }

//*** MÉTODOS COMPLEMENTARIOS***\\


    /**
     * Verifica si un usuario ya existe en la base de datos por su correo institucional.
     *
     * @param instiEmail Correo institucional del usuario.
     * @return true si el correo ya existe, false si no.
     */
    public boolean verifyUserExist(String instiEmail) {
        return userRepository.existsByInstiEmail(instiEmail);
    }

    /**
     * Convierte una entidad de usuario (UserEntity) a un DTO (UserDTO).
     *
     * @param userEntity Entidad del usuario a convertir.
     * @return Objeto UserDTO con los datos convertidos.
     */
    private UserDTO ConvertToDTO(UserEntity userEntity) {
        UserDTO dto = new UserDTO();
        dto.setUserId(userEntity.getUserId());
        dto.setUserName(userEntity.getUserName());
        dto.setLastName(userEntity.getLastName());
        dto.setInstiEmail(userEntity.getInstiEmail());
        dto.setPassword(userEntity.getPassword());
        dto.setGrade(userEntity.getGrade());
        dto.setImagenUrl(userEntity.getImagenUrl());

        // Asigna el nombre y ID del rol si el usuario tiene un rol asociado
        if (userEntity.getRoleId() != null) {
            dto.setRoleName(userEntity.getRoleId().getRoleName());
            dto.setRoleId(userEntity.getRoleId().getRoleId());
        }

        return dto;
    }

    /**
     * Convierte un objeto DTO (UserDTO) en una entidad de usuario (UserEntity).
     *
     * @param json Objeto UserDTO con los datos del usuario.
     * @return Objeto UserEntity con los datos listos para guardar en la base de datos.
     * @throws RoleNotFoundException si el ID del rol no existe en la base.
     */
    private UserEntity ConvertToEntity(@Valid UserDTO json) {
        Argon2Password objHash = new Argon2Password();
        UserEntity entity = new UserEntity();
        entity.setUserName(json.getUserName());
        entity.setLastName(json.getLastName());
        entity.setInstiEmail(json.getInstiEmail());
        entity.setPassword(argon2.EncryptPassword(json.getPassword()));
        entity.setGrade(json.getGrade());
        entity.setImagenUrl(json.getImagenUrl());

        // Asigna el rol si se proporciona un ID de rol
        if (json.getRoleId() != null) {
            RoleEntity entityRole = roleRepository.findById(json.getRoleId())
                    .orElseThrow(() -> new RoleNotFoundException("ID del rol del usuario no encontrado"));
            entity.setRoleId(entityRole);
        }
        return entity;
    }

}
