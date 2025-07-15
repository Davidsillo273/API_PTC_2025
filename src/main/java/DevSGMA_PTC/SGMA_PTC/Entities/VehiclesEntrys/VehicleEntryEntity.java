package DevSGMA_PTC.SGMA_PTC.Entities.VehiclesEntrys;

//Importacion de librerias
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbVehicleEntries")
@Getter
@Setter
public class  VehicleEntryEntity {

    //creamos y ponemos los atributos haciendo relacion a la base de datos del Proyecto
    private String licensePlate;
    private String brand;
    private String model;
    private String notes;

    }

