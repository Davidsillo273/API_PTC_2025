package DevSGMA_PTC.SGMA_PTC.Entities.Roles;
import jakarta.persistence.*;
import lombok.*;

public class RolEntity{

    // Esta clase representa la tabla tbUsers de la base de datos
    @Entity
    @Table(name = "tbRoles")
    @Data // Lombok: genera automáticamente getters, setters, equals, hashCode y toString
    @NoArgsConstructor // Lombok: constructor vacío
    @AllArgsConstructor // Lombok: constructor con todos los campos

    public class Rol {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) // Se genera automáticamente (autoincremental)
        private Long roleId;

        @Column(nullable = false)
        private String roleName;
    }
}
