package DevSGMA_PTC.SGMA_PTC.Models.DTO.VehiclesEntrys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString @EqualsAndHashCode
@Getter @Setter
@Table(name = "tbVehicleEntries")
public class VehicleEntryRequestDTO {

    private String licensePlate;
    private String brand;
    private String model;
    private String notes;


    }
