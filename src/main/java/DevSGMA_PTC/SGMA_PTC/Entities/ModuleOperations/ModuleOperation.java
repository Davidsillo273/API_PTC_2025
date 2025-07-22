package DevSGMA_PTC.SGMA_PTC.Entities.ModuleOperations;

import DevSGMA_PTC.SGMA_PTC.Entities.Modules.Module;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "tbModuleOperations")
@Getter
@Setter
@NoArgsConstructor
public class ModuleOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operationId")
    private Long id;

    @NotBlank(message = "El nombre de la operación es requerido")
    @Size(max = 100, message = "El nombre no debe exceder 100 caracteres")
    @Column(name = "operationName", nullable = false, length = 100)
    private String name;

    @NotNull(message = "El módulo es requerido")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moduleId", nullable = false)
    private Module module;
}