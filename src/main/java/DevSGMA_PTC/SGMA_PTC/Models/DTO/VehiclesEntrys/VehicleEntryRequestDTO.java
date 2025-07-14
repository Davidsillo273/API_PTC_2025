package DevSGMA_PTC.SGMA_PTC.Models.DTO.VehiclesEntrys;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbVehicleEntries")
public class VehicleEntryRequestDTO {
    private String licensePlate; // No necesita @Column
    private String brand;
    private String model;
    private String notes;


    }
