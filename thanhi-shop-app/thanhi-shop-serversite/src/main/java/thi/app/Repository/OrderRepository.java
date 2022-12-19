package thi.app.Repository;

import org.springframework.stereotype.Repository;
import thi.app.model.entity.Order;

import java.util.List;

@Repository
public interface OrderRepository extends SearchRepository<Order, Long> {
    public List<Order> findOrdersByAccount_UsernameOrderByCreateDateDesc(String username);

    public List<Order> findOrdersByStatus_Name(String status);
}