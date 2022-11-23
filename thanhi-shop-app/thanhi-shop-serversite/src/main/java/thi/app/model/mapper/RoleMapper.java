package thi.app.model.mapper;

import org.springframework.stereotype.Service;
import thi.app.model.dto.RoleDTO;
import thi.app.model.entity.Role;

@Service
public interface RoleMapper extends EntityMapper<RoleDTO, Role>{
}
