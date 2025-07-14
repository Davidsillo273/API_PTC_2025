package DevSGMA_PTC.SGMA_PTC.Entities.Users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;

public class UserEntity {

    // Esta clase representa la tabla tbUsers de la base de datos
    @Entity
    @Table(name = "tbUsers")
    @Data // Lombok: genera automáticamente getters, setters, equals, hashCode y toString
    @NoArgsConstructor // Lombok: constructor vacío
    @AllArgsConstructor // Lombok: constructor con todos los campos

    public class User {

        @Id // Llave primaria
        @GeneratedValue(strategy = GenerationType.IDENTITY) // Se genera automáticamente (autoincremental)
        private Long userId;

        @Column(nullable = false, unique = true)
        private String username;

        @Column(nullable = false)
        private String passwordHash;

        @Column(nullable = false)
        private String fullName;

        @ManyToOne // Muchos usuarios pueden tener un mismo rol
        @JoinColumn(name = "roleId", nullable = false) // Columna que conecta con la tabla de roles
        private Role role;}
}
