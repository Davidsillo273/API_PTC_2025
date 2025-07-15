package DevSGMA_PTC.SGMA_PTC.Controller.WorksOrders;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.WorksOrders.WorkOrderRequestDTO;
import DevSGMA_PTC.SGMA_PTC.Services.WorksOrders.WorkOrderService;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Entity
@Table(name = "TBWORKORDER")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class WorkOrderController {

    @Autowired
    WorkOrderService workorder  ;

    //localhost:8080/apiUsuario/datosUsuarios
    @GetMapping("/datosUsuarios")
    public List<WorkOrderRequestDTO> obtenerDatos(){
        return workorder.getallWorkOrders();
}
}
