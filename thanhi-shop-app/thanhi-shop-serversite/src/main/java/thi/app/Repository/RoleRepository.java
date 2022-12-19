package thi.app.Repository;

import org.springframework.stereotype.Repository;
import thi.app.model.entity.Role;

@Repository
public interface RoleRepository extends SearchRepository<Role, String> {
}