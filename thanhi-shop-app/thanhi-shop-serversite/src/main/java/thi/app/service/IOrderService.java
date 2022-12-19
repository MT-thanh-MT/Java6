package thi.app.service;

import thi.app.model.dto.OrderDTO;
import thi.app.model.dto.OrderViewDTO;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    public OrderDTO create(OrderDTO orderDTO);

    List<OrderViewDTO> getOrderViewByUsername(String username);

    List<OrderDTO> getAll();

    OrderDTO update(OrderDTO orderDTO);

    List<OrderDTO> getOrdersByStatus(String status);

    List<OrderDTO> searchOrder(String text, List<String> fields, int limit);

    void indexData() throws InterruptedException;
}
