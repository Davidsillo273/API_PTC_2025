package DevSGMA_PTC.SGMA_PTC.Entities.Vehicles;

import DevSGMA_PTC.SGMA_PTC.Entities.Students.StudentEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrderEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import DevSGMA_PTC.SGMA_PTC.Entities.VehiclesTypes.VehicleTypeEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.List;

// Entity que representa un vehículo en la base de datos
@Entity
@Table(name = "TBVEHICLES")
// Anotaciones de Lombok para generar getters, setters y equals/hashCode automáticamente
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VehicleEntity {
    //*** ATRIBUTOS ***\\

    // ID del vehículo, clave primaria generada automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vehicles")
    @SequenceGenerator(name = "seq_vehicles", sequenceName = "seq_vehicles", allocationSize = 1)
    @Column(name = "VEHICLEID")
    private Long vehicleId;

    // Número de placa, único y obligatorio, máximo 10 caracteres
    @Column(name = "PLATENUMBER", length = 10, nullable = false, unique = true)
    private String plateNumber;

    // Indica si el vehículo tiene póliza (1 = sí, 0 = no)
    @Column(name = "HASPOLICY" , nullable = false)
    private Long hasPolicy;

    // Número de póliza, puede ser nulo si no tiene póliza, máximo 15 caracteres
    @Column(name = "POLICYNUMBER", length = 15)
    private String policyNumber; // Número de póliza, puede ser nulo si no tiene póliza

    // Marca del vehículo, obligatorio, máximo 50 caracteres
    @Column(name = "BRAND", length = 50, nullable = false)
    private String brand;

    // Modelo del vehículo, obligatorio, máximo 50 caracteres
    @Column(name = "MODEL", length = 50 , nullable = false)
    private String model;

    @ManyToOne(fetch = FetchType.LAZY)// Muchos vehículos pueden tener el mismo tipo
    @JoinColumn(name = "TYPEID", referencedColumnName = "TYPEID") // Columna que conecta con la tabla de tipos de vehículo
    private VehicleTypeEntity typeId;

    // Color del vehículo, obligatorio, máximo 30 caracteres
    @Column(name = "COLOR", length = 30, nullable = false)
    private String color;

    // Número de tarjeta de circulación, único y obligatorio, máximo 20 caracteres
    @Column(name = "CIRCULATIONCARDNUMBER", length = 20, nullable = false, unique = true)
    private String circulationCardNumber;

    // Nombre del propietario del vehículo, máximo 100 caracteres
    @Column(name = "OWNERNAME", length = 100)
    private String ownerName;

    // DUI del propietario del vehículo, máximo 10 caracteres
    @Column(name = "OWNERDUI", length = 10)
    private String ownerDui;

    // Teléfono del propietario del vehículo, máximo 10 caracteres
    @Column(name = "OWNERPHONE", length = 10)
    private String ownerPhone;

    // Imagen del vehículo, obligatorio (puede ser URL o base64)
    @Column(name = "VEHICLEIMAGE", nullable = false)
    private String vehicleImage;

    @ManyToOne // Muchos vehículos pueden estar asociados a un mismo estudiante
    @JoinColumn(name = "STUDENTID", referencedColumnName = "STUDENTID") // Columna que conecta con la tabla de estudiantes
    private StudentEntity studentId;

    // Valor de mantenimiento expo, obligatorio
    @Column(name = "MAINTENANCEEXPO", nullable = false)
    private Long maintenanceExpo;

    @Column(name = "IDSTATUS", nullable = false)
    private Long status; // Estado del vehículo (activo/inactivo)

    //*** ONETOMANYS ***\\

    @OneToMany(mappedBy = "vehicleId", cascade = CascadeType.ALL) // Relación OneToMany con tbWorkOrders
    @JsonIgnore
    private List<WorkOrderEntity> workOrder = new ArrayList<>(); // Lista de órdenes de trabajo asociadas al vehículo

    @Override
    public String toString() {
        return "VehicleEntity{" +
                "vehicleId=" + vehicleId +
                ", plateNumber='" + plateNumber + '\'' +
                ", hasPolicy=" + hasPolicy +
                ", policyNumber='" + policyNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", circulationCardNumber='" + circulationCardNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ownerDui='" + ownerDui + '\'' +
                ", ownerPhone='" + ownerPhone + '\'' +
                ", vehicleImage='" + vehicleImage + '\'' +
                ", maintenanceExpo=" + maintenanceExpo +
                ", status=" + status +
                '}';
    }
}
