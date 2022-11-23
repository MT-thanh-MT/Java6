package thi.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thi.app.model.entity.Status;

public interface StatusRepository extends JpaRepository<Status, String> {
}