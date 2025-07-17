package DevSGMA_PTC.SGMA_PTC.Entities;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;

@Entity
@Table(name = "tbVehicles")
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    @Column(name = "plateNumber")
    private String plateNumber;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @ManyToOne
    @JoinColumn(name = "typeId", nullable = false)
    private vehicleTypeEntity type;

    @Column(name = "typeId")
    private Long typeId;

    @Column(name = "color")
    private String color;

    @Column(name = "circulationNumber")
    private String circulationNumber;

    //falta el de las imagenes




}
