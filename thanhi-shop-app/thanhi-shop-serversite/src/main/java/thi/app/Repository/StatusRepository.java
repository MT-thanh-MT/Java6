package thi.app.Repository;

import org.springframework.stereotype.Repository;
import thi.app.model.entity.Status;

@Repository
public interface StatusRepository extends SearchRepository<Status, String> {
}