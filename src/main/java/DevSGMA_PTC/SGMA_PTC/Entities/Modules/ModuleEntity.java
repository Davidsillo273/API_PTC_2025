package DevSGMA_PTC.SGMA_PTC.Entities.Modules;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;
import DevSGMA_PTC.SGMA_PTC.Entities.ModulesOperations.ModuleOperationEntity;

@Entity
@Table(name = "tbModules")
public class ModuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moduleId;

    @Column(nullable = false, length = 50)
    private String moduleName;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private List<ModuleOperationEntity> operations;

    public ModuleEntity() {}

    public ModuleEntity(Long moduleId, String moduleName, List<ModuleOperationEntity> operations) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.operations = operations;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<ModuleOperationEntity> getOperations() {
        return operations;
    }

    public void setOperations(List<ModuleOperationEntity> operations) {
        this.operations = operations;
    }
}
