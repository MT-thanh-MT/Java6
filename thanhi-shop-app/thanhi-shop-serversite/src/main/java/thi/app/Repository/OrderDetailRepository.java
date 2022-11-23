package thi.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thi.app.model.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}