package DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders;

import DevSGMA_PTC.SGMA_PTC.Entities.Modules.ModuleEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Vehicles.VehicleEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Entries.EntryEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity // Indica que esta clase es una entidad JPA y se mapea a una tabla en la base de datos
@Getter // Lombok: genera automáticamente los métodos getter
@Setter // Lombok: genera automáticamente los métodos setter
@EqualsAndHashCode // Lombok: genera automáticamente equals y hashCode
@Table(name = "TBWORKORDERS") // Especifica el nombre de la tabla en la base de datos
public class WorkOrderEntity {

    //*** ATRIBUTOS ***\\

    // ID de la orden de trabajo, clave primaria generada automáticamente
    @Id
    @Column(name = "WORKORDERID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_work_orders")
    @SequenceGenerator(name = "seq_work_orders", sequenceName = "seq_work_orders", allocationSize = 1)
    private Long workOrderId;

    @ManyToOne // Muchas órdenes de trabajo pueden estar asociadas a un mismo vehículo
    @JoinColumn(name = "VEHICLEID", referencedColumnName = "VEHICLEID") // Columna que conecta con la tabla de Vehículos
    private VehicleEntity vehicleId;

    @ManyToOne // Muchas órdenes de trabajo pueden estar asociadas a un mismo módulo académico
    @JoinColumn(name = "MODULEID", referencedColumnName = "MODULEID") // Columna que conecta con la tabla de Módulos
    private ModuleEntity moduleId;

    // Tiempo estimado para la orden de trabajo (puede ser en horas, minutos, etc.)
    @Column(name = "ESTIMATEDTIME", length = 5)
    private String estimatedTime;

    // Imagen asociada a la orden de trabajo (puede ser URL o base64)
    @Column(name = "WORKORDERIMAGE")
    private String workOrdersImage;

    // Estado de la orden de trabajo (por ejemplo: pendiente, en proceso, finalizada)
    @Column(name = "STATUS")
    private Long status;

    //*** ONETOMANYS ***\\

    @OneToMany(mappedBy = "workOrderId", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Relación OneToMany con tbEntries
    @JsonIgnore
    private List<EntryEntity> entry = new ArrayList<>(); // Lista de entradas asociadas a la orden de trabajo

    @Override
    public String toString() {
        return "WorkOrderEntity{" +
                "workOrderId=" + workOrderId +
                ", vehicleId=" + vehicleId +
                ", moduleId=" + moduleId +
                ", estimatedTime='" + estimatedTime + '\'' +
                ", workOrdersImage='" + workOrdersImage + '\'' +
                ", status=" + status +
                ", entry=" + entry +
                '}';
    }
}