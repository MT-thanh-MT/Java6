package thi.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thi.app.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
}