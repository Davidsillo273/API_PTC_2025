package DevSGMA_PTC.SGMA_PTC.Controller.VehiclesTypes;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.VehiclesTypes.VehicleTypeRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.VehiclesTypes.VehicleTypeResponseDTO;
import DevSGMA_PTC.SGMA_PTC.Services.VehiclesTypes.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    }


