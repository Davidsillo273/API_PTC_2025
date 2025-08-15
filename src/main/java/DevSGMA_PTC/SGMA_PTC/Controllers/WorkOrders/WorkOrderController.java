package DevSGMA_PTC.SGMA_PTC.Controllers.WorkOrders;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import DevSGMA_PTC.SGMA_PTC.Models.ApiRespones.APIResponse;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.WorkOrders.WorkOrderDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrderEntity;
import DevSGMA_PTC.SGMA_PTC.Services.WorkOrders.WorkOrderService;

@RestController
@RequestMapping("/api/workorders")
public class WorkOrderController {

    @Autowired
    private  WorkOrderService workOrderService;

    @GetMapping("/getDataWorkOrders")
    public ResponseEntity<APIResponse<Page<WorkOrderDTO>>> getAllUsers(
            @RequestParam(defaultValue = "0") int page, //Pagina por defecto 0
            @RequestParam(defaultValue = "10") int size
    ){
        //validacion del tema;o de la pagina: debe estar entre 1 y 50
        if(size <= 0 || size > 50) {
            ResponseEntity.badRequest().body(Map.of(
                    "status", "La páginación de datos debe estar entre 1 y 50"
            ));
            return ResponseEntity.ok(null); // Devuelve nulo si la validación falla
        }

        //Obtiene los usuarios paginados desdew el servicio
        Page<WorkOrderDTO> WorkOrders = workOrderService.getAllUsers(page, size);

        // SI ocurre u nerror al obtener los datos
        if( WorkOrders == null){
            ResponseEntity.badRequest().body(Map.of(
                    "status", "Error al obtener datos"
            ));
        }
        //Retorna respuesta exitosa con los datos
        return ResponseEntity.ok(APIResponse.success("Datps consultados correctamente", WorkOrders));
    }
}
