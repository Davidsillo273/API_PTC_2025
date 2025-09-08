package DevSGMA_PTC.SGMA_PTC.Controllers.Roles;


import DevSGMA_PTC.SGMA_PTC.Models.DTO.Roles.RoleDTO;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.VehicleType.VehicleTypeDTO;
import DevSGMA_PTC.SGMA_PTC.Services.Roles.RoleService;
import DevSGMA_PTC.SGMA_PTC.Services.VehicleType.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Roles")
public class RoleController {
    @Autowired
    private RoleService service;

    @GetMapping("/getAllRoles")
    public List<RoleDTO> getAll() {
        return service.getAllVehicleTypes();
    }
}
