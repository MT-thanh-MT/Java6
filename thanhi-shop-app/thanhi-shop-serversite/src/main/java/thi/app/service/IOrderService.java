package thi.app.service;

import thi.app.model.dto.OrderDTO;

public interface IOrderService {
    public OrderDTO create(OrderDTO orderDTO);
}
