package thi.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thi.app.model.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findOrdersByAccount_UsernameOrderByCreateDateDesc(String username);

    public List<Order> findOrdersByStatus_Name(String status);
}