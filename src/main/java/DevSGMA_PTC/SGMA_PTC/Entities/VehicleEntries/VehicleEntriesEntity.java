package DevSGMA_PTC.SGMA_PTC.Entities.VehicleEntries;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "TBVEHICLEENTRIES")
public class VehicleEntriesEntity {

    @Column(name = "ENTRYID")
    private Number entryId;

    @ManyToOne
    @JoinColumn(name = "ENTRYTIME", nullable = false )
    private Long entryTime;

    @Column(name = "OPERATIONID")
    private Number operationId;

    @Column(name = "STATUS")
    private String Status;
}
