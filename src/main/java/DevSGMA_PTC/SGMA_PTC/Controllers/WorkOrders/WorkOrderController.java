package DevSGMA_PTC.SGMA_PTC.Controllers.WorkOrders;

import DevSGMA_PTC.SGMA_PTC.Exceptions.WorkOrders.ExceptionWorkOrdernotfound;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.WorkOrder.WorkOrderDTO;
import DevSGMA_PTC.SGMA_PTC.Services.WorkOrders.WorkOrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/WorkOrders")
@CrossOrigin
public class WorkOrderController {

    @Autowired
    private WorkOrderService service;

    /***
     *
     * @param page Aqui definimos una variable page de tipo entero
     * @param size Definimos una variable size de tipo entero
     * @return en la parte del FrontEnd devolvera la vista en forma de paginación
     */
    @GetMapping("/getAllWorkOrders")
    private ResponseEntity<Page<WorkOrderDTO>> getDataWorkOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        if (size <= 0 || size > 50){
            ResponseEntity.badRequest().body(Map.of(
                    "status", "El tamaño de la página debe estar entre 1 y 50"
            ));
            return ResponseEntity.ok(null);
        }
        Page<WorkOrderDTO> WorkOrders = service.getAllWorkOrders(page, size);
        if (WorkOrders == null){
            ResponseEntity.badRequest().body(Map.of(
                    "status", "No hay Ordenes de trabajo registradas"
            ));
        }
        return ResponseEntity.ok(WorkOrders);
    }

    /***
     *
     * @param json json sera nuestra variable que literalmente sera una herramienta que nos permitira ocuparla como parametro
     * @param request con el request hacemos la solicitud, una solicitud para ingresar una nueva orden de trabajo
     * @return el return puede variar, ya sea devolvemos si la insercion no pudo completarse,
     * el segundo caso es que exitosamente pudo crearse
     * y finalmente como tercer caso, un error que no pudo crearse la orden de trabajo
     */
    @PostMapping("/newWorkOrders")
    private ResponseEntity<Map<String, Object>> insertWorOrder(@Valid @RequestBody WorkOrderDTO json, HttpServletRequest request){
        try{
            WorkOrderDTO response =service.insert(json);
            if (response == null){
                return ResponseEntity.badRequest().body(Map.of(
                        "Error", "Inserción incorrecta",
                        "Estatus", "Inserción incorrecta",
                        "Descripción", "Verifique que los campos esten correctamente ingresados"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "Estado", "Completado",
                    "data", response
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "status", "error",
                            "message", "Error al registrar nueva orden de trabajo",
                            "detail", e.getMessage()
                    ));
        }
    }

    @PutMapping("/updateWorkOrder/{id}")
    public ResponseEntity<?> modifyWorkOrder(
            @PathVariable Long id,
            @Valid @RequestBody WorkOrderDTO WorkOrders,
            BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }

        try{
            WorkOrderDTO workOrderUpdated = service.update(id, WorkOrders);
            return ResponseEntity.ok(workOrderUpdated);
        }
        catch (ExceptionWorkOrdernotfound e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deleteWorkOrder/{id}")
    public ResponseEntity<Map<String, Object>> DeleteWorkOrder(@PathVariable Long id) {
        try {
            // Intenta eliminar Orden de trabajo por medio del obj service
            // Si el metodo delete devuelve false, no se ha encontrado ninguna orden de trabajo
            if (!service.delete(id)) {
                // Retorna un error de 404 NO HA SIDO ENCONTRADO
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        // Agrega un header personalizado
                        .header("X-Mensaje-Error", "WorkOrderNotFound")
                        // Cuerpo de la respuesta con detalles del error
                        .body(Map.of(
                                "error", "Not found",  // Tipo de error
                                "mensaje", "La orden de trabajo no ha sido encontrada",  // Mensaje descriptivo
                                "timestamp", Instant.now().toString()  // Marca de tiempo del error
                        ));
            }

            // Si la eliminación fue exitosa, entonces retorna success 200 (OK): PROCESO COMPLETADO.
            return ResponseEntity.ok().body(Map.of(
                    "status", "Proceso completado",  // Estado de la operación
                    "message", "Orden de trabajo eliminada exitosamente"  // Mensaje de éxito
            ));

        } catch (Exception e) {
            // Si ocurre cualquier error inesperado, retorna 500 (Internal Server Error)
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "Error",  // Indicador de error
                    "message", "Error al eliminar la orden de trabajo",  // Mensaje general
                    "detail", e.getMessage()  // Detalles tecnicos del error (DEBUGGIN)
            ));
        }
    }


}