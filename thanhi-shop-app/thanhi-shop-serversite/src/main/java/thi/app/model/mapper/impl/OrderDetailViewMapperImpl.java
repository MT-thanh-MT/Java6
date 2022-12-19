package thi.app.model.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thi.app.model.dto.OrderDetailViewDTO;
import thi.app.model.entity.Order;
import thi.app.model.entity.OrderDetail;
import thi.app.model.entity.Product;
import thi.app.model.mapper.OrderDetailViewMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//@Service
public class OrderDetailViewMapperImpl implements OrderDetailViewMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderDetail toEntity(OrderDetailViewDTO dto) {
        OrderDetail entity = modelMapper.map(dto, OrderDetail.class);
        Product p = new Product();
        p.setId(dto.getProductId());
        entity.setProduct(p);
        Order order = new Order();
        order.setId(dto.getOrderId());
        entity.setOrder(order);

        return entity;
    }

    @Override
    public OrderDetailViewDTO toDto(OrderDetail entity) {
        OrderDetailViewDTO dto = modelMapper.map(entity, OrderDetailViewDTO.class);
        dto.setProductName(entity.getProduct().getName());
        dto.setProductId(entity.getProduct().getId());
        dto.setProductImageUrl(entity.getProduct().getImageUrl());
        dto.setSubCategoryID(entity.getProduct().getSubCategory().getId());
        dto.setSubCategoryName(entity.getProduct().getSubCategory().getName());

        return dto;
    }

    @Override
    public List<OrderDetail> toEntity(List<OrderDetailViewDTO> dtoList) {
        return dtoList.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailViewDTO> toDto(List<OrderDetail> entityList) {
        return entityList.stream()
                .filter(Objects::nonNull)
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
