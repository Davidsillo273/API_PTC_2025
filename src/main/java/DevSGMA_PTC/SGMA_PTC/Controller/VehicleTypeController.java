package DevSGMA_PTC.SGMA_PTC.Controller;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.VehicleTypeRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.VehicleTypeResponseDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.VehicleTypeRepository;
import DevSGMA_PTC.SGMA_PTC.Service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API_PTC/vehicleTypes")

public class VehicleTypeController {

    @Autowired
    private VehicleTypeService service;

    @GetMapping
    public List<VehicleTypeResponseDTO> obtenerDatos(){
        return service.obtenerTodo();
    }

    @PostMapping
    public ResponseEntity<VehicleTypeResponseDTO> create(@RequestBody VehicleTypeRequestDTO dto) {
        VehicleTypeResponseDTO created = service.save(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
        
    }






