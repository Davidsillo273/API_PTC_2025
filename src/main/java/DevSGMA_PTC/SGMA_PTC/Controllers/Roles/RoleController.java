package DevSGMA_PTC.SGMA_PTC.Controllers.Roles;


import DevSGMA_PTC.SGMA_PTC.Exceptions.Grades.ExceptionGradeNotFound;
import DevSGMA_PTC.SGMA_PTC.Models.ApiResponse.ApiResponse;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Roles.RoleDTO;
import DevSGMA_PTC.SGMA_PTC.Services.Roles.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //*** MÉTODO PARA OBTENER TODOS LOS ROLES ***\\
    @GetMapping("/getAllRoles")
    public ResponseEntity<ApiResponse<List<RoleDTO>>> getAllRoles() {
        List<RoleDTO> roles = roleService.getAllRoles();
        if (roles == null || roles.isEmpty()) {
            throw new ExceptionGradeNotFound("No se encontraron roles");
        }
        return ResponseEntity.ok(ApiResponse.success("Roles consultados correctamente", roles));
    }
}
