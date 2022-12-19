package thi.app.model.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thi.app.model.dto.OrderDTO;
import thi.app.model.entity.Account;
import thi.app.model.entity.Order;
import thi.app.model.mapper.OrderMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//@Service
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderDetailMapperImpl orderDetailMapper;

    @Override
    public Order toEntity(OrderDTO dto) {
        Order order = modelMapper.map(dto, Order.class);
        Account account = new Account();
        account.setId(dto.getAccountId());
        order.setAccount(account);
        order.setOrderDetails(orderDetailMapper.toEntity(dto.getOrderDetails().stream().toList()).stream().collect(Collectors.toSet()));
        return order;
    }

    @Override
    public OrderDTO toDto(Order entity) {
        OrderDTO orderDTO = modelMapper.map(entity, OrderDTO.class);
        orderDTO.setAccountId(entity.getAccount().getId());
        orderDTO.setOrderDetails(orderDetailMapper.toDto(entity.getOrderDetails().stream().toList()).stream().collect(Collectors.toSet()));
        return orderDTO;
    }

    @Override
    public List<Order> toEntity(List<OrderDTO> dtoList) {
        return dtoList.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> toDto(List<Order> entityList) {
        return entityList.stream()
                .filter(Objects::nonNull)
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
