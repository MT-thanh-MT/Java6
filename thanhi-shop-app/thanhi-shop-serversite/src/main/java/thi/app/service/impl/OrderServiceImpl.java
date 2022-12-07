package thi.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thi.app.Repository.OrderDetailRepository;
import thi.app.Repository.OrderRepository;
import thi.app.model.dto.OrderDTO;
import thi.app.model.dto.OrderViewDTO;
import thi.app.model.entity.Order;
import thi.app.model.entity.OrderDetail;
import thi.app.model.mapper.OrderMapper;
import thi.app.model.mapper.OrderViewMapper;
import thi.app.service.IOrderService;
import thi.app.web.errors.OrderNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderViewMapper orderViewMapper;

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

    @Override
    public List<OrderViewDTO> getOrderViewByUsername(String username) {
        List<Order> orderList = orderRepo.findOrdersByAccount_UsernameOrderByCreateDateDesc(username);
        if (orderList.isEmpty()) {
            throw new OrderNotFoundException("No Order!");
        } else {
            return orderViewMapper.toDto(orderList);
        }
    }

    @Override
    public List<OrderDTO> getAll() {
        List<OrderDTO> list = orderMapper.toDto(orderRepo.findAll());
        return list;
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        order = orderRepo.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderDTO> getOrdersByStatus(String status) {
        List<OrderDTO> list = orderMapper.toDto(orderRepo.findOrdersByStatus_Name(status));
        return list;
    }
}
