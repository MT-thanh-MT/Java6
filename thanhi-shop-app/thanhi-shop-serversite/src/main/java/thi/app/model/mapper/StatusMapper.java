package thi.app.model.mapper;

import org.mapstruct.Mapper;
import thi.app.model.dto.StatusDTO;
import thi.app.model.entity.Status;

@Mapper(componentModel = "spring", uses = {})
public interface StatusMapper extends EntityMapper<StatusDTO, Status> {

    default Status fromId(String id) {
        if (id == null) {
            return null;
        }
        Status status = new Status();
        status.setName(id);
        return status;
    }

}
