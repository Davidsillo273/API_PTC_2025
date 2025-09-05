package DevSGMA_PTC.SGMA_PTC.Entities.Entries;

import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrderEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity // Indica que esta clase es una entidad JPA y se mapea a una tabla en la base de datos
@Getter // Lombok: genera automáticamente los métodos getter
@Setter // Lombok: genera automáticamente los métodos setter
@ToString // Lombok: genera automáticamente el método toString
@EqualsAndHashCode // Lombok: genera automáticamente equals y hashCode
@Table(name = "TBENTRIES") // Especifica el nombre de la tabla en la base de datos
public class EntryEntity {

    //*** ATRIBUTOS ***\\
    @Id
    @Column(name = "ENTRYID") // Mapea el campo entryId con la columna ENTRYID de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long entryId; // Identificador único de la entrada

    @Column(name = "ENTRYDATE") // Mapea el campo entryDate con la columna ENTRYDATE de la tabla
    private LocalDate entryDate; // Fecha/hora de la entrada

    @ManyToOne // Relación muchos a uno: varias entradas pueden estar asociadas a una misma orden de trabajo
    @JoinColumn(name = "WORKORDERID", referencedColumnName = "WORKORDERID") // Relaciona con la columna WORKORDERID de la tabla de órdenes de trabajo
    private WorkOrderEntity workOrderId; // Referencia a la orden de trabajo asociada a la entrada
}
