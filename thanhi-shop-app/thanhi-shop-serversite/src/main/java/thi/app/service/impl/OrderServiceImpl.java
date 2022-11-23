package thi.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thi.app.Repository.OrderDetailRepository;
import thi.app.Repository.OrderRepository;
import thi.app.model.dto.OrderDTO;
import thi.app.model.entity.Order;
import thi.app.model.entity.OrderDetail;
import thi.app.model.mapper.OrderMapper;
import thi.app.service.IOrderService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    OrderDetailRepository orderDetailRepo;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        Order order = orderRepo.save(orderMapper.toEntity(orderDTO));

        List<OrderDetail> orderDetails = order.getOrderDetails()
                .stream()
                .map(orderDetail -> {
                    orderDetail.setOrder(order);
                    return orderDetail;
                })
                .collect(Collectors.toList());
        orderDetailRepo.saveAll(orderDetails);

        return orderMapper.toDto(order);
    }
}
