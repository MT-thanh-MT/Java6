package thi.app.model.mapper;

import org.springframework.stereotype.Service;
import thi.app.model.dto.StatusDTO;
import thi.app.model.entity.Status;

@Service
public interface StatusMapper extends EntityMapper<StatusDTO, Status> {
}
