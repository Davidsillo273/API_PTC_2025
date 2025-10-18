package DevSGMA_PTC.SGMA_PTC.Services.Observations;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.Observations.ObservationDTO;

import java.util.List;
import java.util.Map;

public interface ObservationService {
    ObservationDTO createObservation(ObservationDTO dto) throws Exception;
    List<ObservationDTO> getObservationsByWorkOrderId(Long workOrderId);
}

