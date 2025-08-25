package DevSGMA_PTC.SGMA_PTC.Controllers.Vehicle;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.Vehicles.VehiclesDTO;
import DevSGMA_PTC.SGMA_PTC.Services.Vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehiclesService;


    @PostMapping
    public VehiclesDTO save(@RequestBody VehiclesDTO dto) {
        return vehiclesService.save(dto);
    }


    @GetMapping
    public List<VehiclesDTO> getAll() {
        return vehiclesService.getAll();
    }


    @GetMapping("/{id}")
    public Optional<VehiclesDTO> getById(@PathVariable Long id) {
        return vehiclesService.getById(id);
    }


    @GetMapping("/plate/{plateNumber}")
    public Optional<VehiclesDTO> getByPlate(@PathVariable String plateNumber) {
        return vehiclesService.getByPlate(plateNumber);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vehiclesService.delete(id);
    }
}