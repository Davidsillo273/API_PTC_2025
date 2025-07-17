package DevSGMA_PTC.SGMA_PTC.Controller.VehiclesEntrys;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.Vehicles.VehicleRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.VehiclesEntrys.VehicleEntryRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Services.VehiclesEntrys.VehicleEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.List;

@RestController
@RequestMapping("/api/vehicle-entries")
public class  VehicleEntryController {

        @Autowired
         VehicleEntryService VehicleEntries;

        // GET /api/roles para listar los roles
        @GetMapping
        public List<VehicleEntryRequestDTO> getallVehicleEntrys() {
            return VehicleEntries.VehicleEntries();
        }

    }


