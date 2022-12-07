package thi.app.model.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thi.app.model.dto.OrderViewDTO;
import thi.app.model.entity.Account;
import thi.app.model.entity.Order;
import thi.app.model.entity.Status;
import thi.app.model.mapper.OrderDetailViewMapper;
import thi.app.model.mapper.OrderViewMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderViewMapperImpl implements OrderViewMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderDetailViewMapper orderDetailViewMapper;

    @Override
    public Order toEntity(OrderViewDTO dto) {
        Order order = modelMapper.map(dto, Order.class);
        Account account = new Account();
        account.setId(dto.getAccountId());
        order.setAccount(account);
        order.setStatus(new Status(dto.getStatus(), null));
        order.setOrderDetails(orderDetailViewMapper.toEntity(dto.getOrderDetailView().stream().toList()).stream().collect(Collectors.toSet()));
        return order;
    }

    @Override
    public OrderViewDTO toDto(Order entity) {
        OrderViewDTO dto = modelMapper.map(entity, OrderViewDTO.class);
        dto.setAccountId(entity.getAccount().getId());
        dto.setPayer(entity.getAccount().getUsername());
        dto.setStatus(entity.getStatus().getName());
        dto.setOrderDetailView(orderDetailViewMapper.toDto(entity.getOrderDetails().stream().toList()).stream().collect(Collectors.toSet()));
        return dto;
    }

    @Override
    public List<Order> toEntity(List<OrderViewDTO> dtoList) {
        return dtoList.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderViewDTO> toDto(List<Order> entityList) {
        return entityList.stream()
                .filter(Objects::nonNull)
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
