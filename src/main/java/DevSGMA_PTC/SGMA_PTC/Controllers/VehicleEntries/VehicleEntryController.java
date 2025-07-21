package DevSGMA_PTC.SGMA_PTC.Controllers.VehicleEntries;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import DevSGMA_PTC.SGMA_PTC.Entities.VehicleEntries.VehicleEntry;
import DevSGMA_PTC.SGMA_PTC.Services.VehicleEntries.VehicleEntryService;

@RestController
@RequestMapping("/api/vehicleentries")
public class VehicleEntryController {

    private final VehicleEntryService vehicleEntryService;

    public VehicleEntryController(VehicleEntryService vehicleEntryService) {
        this.vehicleEntryService = vehicleEntryService;
    }

    @GetMapping
    public List<VehicleEntry> getAllEntries() {
        return vehicleEntryService.getAllEntries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleEntry> getEntryById(@PathVariable Long id) {
        Optional<VehicleEntry> entry = vehicleEntryService.getEntryById(id);
        return entry.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public VehicleEntry createEntry(@RequestBody VehicleEntry entry) {
        return vehicleEntryService.createEntry(entry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleEntry> updateEntry(@PathVariable Long id, @RequestBody VehicleEntry entry) {
        VehicleEntry updated = vehicleEntryService.updateEntry(id, entry);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        boolean deleted = vehicleEntryService.deleteEntry(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
