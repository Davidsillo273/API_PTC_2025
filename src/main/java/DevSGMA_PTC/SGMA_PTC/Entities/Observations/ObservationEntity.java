package DevSGMA_PTC.SGMA_PTC.Entities.Observations;

import DevSGMA_PTC.SGMA_PTC.Entities.Students.StudentEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrderEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "TBOBSERVATIONS")
public class ObservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_observations_trabajos")
    @SequenceGenerator(name = "seq_observations_trabajos", sequenceName = "seq_observations_trabajos", allocationSize = 1)
    @Column(name = "OBSERVACIONID")
    private Long observacionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORKORDERID", referencedColumnName = "WORKORDERID")
    private WorkOrderEntity workOrderId;

    @Column(name = "OBSERVACION", length = 500)
    private String observacion;

    @Column(name = "IMAGEURL")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENTID", referencedColumnName = "STUDENTID")
    private StudentEntity studentId;

}

