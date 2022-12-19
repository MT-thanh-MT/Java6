package thi.app.model.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thi.app.model.dto.OrderDetailDTO;
import thi.app.model.entity.Order;
import thi.app.model.entity.OrderDetail;
import thi.app.model.entity.Product;
import thi.app.model.mapper.OrderDetailMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//@Service
public class OrderDetailMapperImpl implements OrderDetailMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderDetail toEntity(OrderDetailDTO dto) {
        OrderDetail orderDetail = modelMapper.map(dto, OrderDetail.class);
        Order order = new Order();
        order.setId(dto.getOrderId());
        orderDetail.setOrder(order);
        Product product = new Product();
        product.setId(dto.getProductId());
        orderDetail.setProduct(product);
        return orderDetail;
    }

    @Override
    public OrderDetailDTO toDto(OrderDetail entity) {
        OrderDetailDTO orderDetailDTO = modelMapper.map(entity, OrderDetailDTO.class);
        orderDetailDTO.setOrderId(entity.getOrder().getId());
        orderDetailDTO.setProductId(entity.getProduct().getId());
        return orderDetailDTO;
    }

    @Override
    public List<OrderDetail> toEntity(List<OrderDetailDTO> dtoList) {
        return dtoList.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailDTO> toDto(List<OrderDetail> entityList) {
        return entityList.stream()
                .filter(Objects::nonNull)
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
