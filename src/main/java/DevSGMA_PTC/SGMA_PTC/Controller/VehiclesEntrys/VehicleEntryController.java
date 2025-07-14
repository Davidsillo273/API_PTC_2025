package DevSGMA_PTC.SGMA_PTC.Controller.VehiclesEntrys;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.VehiclesEntrys.VehicleEntryRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Services.VehiclesEntrys.VehicleEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vehicle-entries")
public class VehicleEntryController {

//    @Autowired
//    private VehicleEntryService vehicleEntryService;
//
//    @GetMapping
//    public List<VehicleEntry> getAll() {
//        return vehicleEntryService.getAllVehicleEntries();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<VehicleEntryRequestDTO> getById(@PathVariable Long id) {
//        return ResponseEntity.ok(vehicleEntryService.getVehicleEntryById(id));
//    }
//
//    @PostMapping
//    public VehicleEntry create(@RequestBody VehicleEntryRequestDTO dto) {
//        return vehicleEntryService.createFromDTO(dto);
//    }
//
//    @PutMapping("/{id}")
//    public VehicleEntry update(@PathVariable Long id, @RequestBody VehicleEntry vehicleEntry) {
//        return vehicleEntryService.updateVehicleEntry(id, vehicleEntry);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        vehicleEntryService.deleteVehicleEntry(id);
//        return ResponseEntity.noContent().build();
   // }
}

