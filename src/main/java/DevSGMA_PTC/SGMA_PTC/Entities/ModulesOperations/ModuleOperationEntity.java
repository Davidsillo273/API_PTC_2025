package DevSGMA_PTC.SGMA_PTC.Entities.ModulesOperations;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleEntity;

@Entity
@Table(name = "tbModuleOperations")
public class ModuleOperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operationId;

    @ManyToOne
    @JoinColumn(name = "moduleId", nullable = false)
    private ModuleEntity module;

    @Column(nullable = false, length = 100)
    private String operationName;

    public ModuleOperationEntity() {}

    public ModuleOperationEntity(Long operationId, ModuleEntity module, String operationName) {
        this.operationId = operationId;
        this.module = module;
        this.operationName = operationName;
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public ModuleEntity getModule() {
        return module;
    }

    public void setModule(ModuleEntity module) {
        this.module = module;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
