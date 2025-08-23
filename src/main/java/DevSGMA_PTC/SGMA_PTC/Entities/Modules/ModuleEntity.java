package DevSGMA_PTC.SGMA_PTC.Entities.Modules;

import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrder.WorkOrderEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "TBMODULES")
public class ModuleEntity {


    @Column(name = "MODULEID")
    private long moduleId;

    @Column(name = "MODULENAME")
    private Long moduleName;

    //*** ONETOMANYS ***\\

    @OneToMany(mappedBy = "moduleId", cascade = CascadeType.ALL) // Relación OneToMany con WorkOrderEntity
    private List<WorkOrderEntity> module = new ArrayList<>(); // Relación OneToMany con WorkOrderEntity

}
