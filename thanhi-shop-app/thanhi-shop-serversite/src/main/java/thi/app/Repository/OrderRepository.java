package thi.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thi.app.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}