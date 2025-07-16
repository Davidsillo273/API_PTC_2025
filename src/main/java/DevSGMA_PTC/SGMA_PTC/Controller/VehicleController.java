package DevSGMA_PTC.SGMA_PTC.Controller;


import DevSGMA_PTC.SGMA_PTC.Models.DTO.VehicleResponseDTO;
import DevSGMA_PTC.SGMA_PTC.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.RecursiveTask;

@RestController
@RequestMapping("/API_PTC/Vehicles")



public class VehicleController {

    @Autowired
    private VehicleService service;


    @GetMapping
    public List<VehicleResponseDTO> obtenerDatos() {
    return service.obtenerTodo();
    }
}

