package thi.app.model.mapper;

import org.mapstruct.Mapper;
import thi.app.model.dto.RoleDTO;
import thi.app.model.entity.Role;

@Mapper(componentModel = "spring", uses = {})
public interface RoleMapper extends EntityMapper<RoleDTO, Role>{

    default Role fromId(String id) {
        if (id == null) {
            return null;
        }
        Role role = new Role();
        role.setName(id);
        return role;
    }
}
