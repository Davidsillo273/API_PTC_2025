package DevSGMA_PTC.SGMA_PTC.Services.Observations;

import DevSGMA_PTC.SGMA_PTC.Entities.Observations.ObservationEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.Students.StudentEntity;
import DevSGMA_PTC.SGMA_PTC.Entities.WorkOrders.WorkOrderEntity;
import DevSGMA_PTC.SGMA_PTC.Models.DTO.Observations.ObservationDTO;
import DevSGMA_PTC.SGMA_PTC.Repositories.Observations.ObservationRepository;
import DevSGMA_PTC.SGMA_PTC.Repositories.Students.StudentsRepository;
import DevSGMA_PTC.SGMA_PTC.Repositories.WorkOrders.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ObservationServiceImpl implements ObservationService {

    @Autowired
    private ObservationRepository observationRepository;

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private StudentsRepository studentsRepository;

    @Override
    public ObservationDTO createObservation(ObservationDTO dto) throws Exception {
        if (dto == null) throw new IllegalArgumentException("Payload nulo");

        Optional<WorkOrderEntity> woOpt = workOrderRepository.findById(dto.getWorkOrderId());
        if (woOpt.isEmpty()) throw new Exception("WorkOrder no encontrada");

        Optional<StudentEntity> stOpt = studentsRepository.findById(dto.getStudentId());
        if (stOpt.isEmpty()) throw new Exception("Student no encontrado");

        ObservationEntity entity = new ObservationEntity();
        entity.setWorkOrderId(woOpt.get());
        entity.setObservacion(dto.getObservacion());
        entity.setImageUrl(dto.getImageUrl());
        entity.setStudentId(stOpt.get());

        ObservationEntity saved = observationRepository.save(entity);

        ObservationDTO out = new ObservationDTO();
        out.setObservacionId(saved.getObservacionId());
        out.setWorkOrderId(saved.getWorkOrderId().getWorkOrderId());
        out.setObservacion(saved.getObservacion());
        out.setImageUrl(saved.getImageUrl());
        out.setStudentId(saved.getStudentId().getStudentId());
        out.setStudentName(saved.getStudentId().getFirstName() + " " + saved.getStudentId().getLastName());

        return out;
    }

    @Override
    public List<ObservationDTO> getObservationsByWorkOrderId(Long workOrderId) {
        List<ObservationEntity> list = observationRepository.findByWorkOrderId_WorkOrderId(workOrderId);
        return list.stream().map(o -> {
            ObservationDTO dto = new ObservationDTO();
            dto.setObservacionId(o.getObservacionId());
            dto.setWorkOrderId(o.getWorkOrderId() != null ? o.getWorkOrderId().getWorkOrderId() : null);
            dto.setObservacion(o.getObservacion());
            dto.setImageUrl(o.getImageUrl());
            if (o.getStudentId() != null) {
                dto.setStudentId(o.getStudentId().getStudentId());
                dto.setStudentName(o.getStudentId().getFirstName() + " " + o.getStudentId().getLastName());
            }
            return dto;
        }).collect(Collectors.toList());
    }
}

