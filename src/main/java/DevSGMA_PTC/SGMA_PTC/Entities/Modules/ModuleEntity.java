package DevSGMA_PTC.SGMA_PTC.Entities.Modules;

import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrder.WorkOrderEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "TBMODULES")
public class ModuleEntity {

    @OneToMany
    @Column(name = "MODULEID")
    private WorkOrderEntity moduleId;

    @Column(name = "MODULENAME")
    private Long moduleName;
}
