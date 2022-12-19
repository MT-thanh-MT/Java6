package thi.app.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import thi.app.model.dto.OrderDetailDTO;
import thi.app.model.dto.OrderDetailViewDTO;
import thi.app.model.entity.OrderDetail;

@Mapper(componentModel = "spring", uses = {OrderMapper.class, ProductMapper.class})
public interface OrderDetailViewMapper extends EntityMapper<OrderDetailViewDTO, OrderDetail>{

    @Mapping(source = "orderId", target = "order")
    @Mapping(source = "productId", target = "product")
    OrderDetail toEntity(OrderDetailViewDTO orderDetailViewDTO);

    @Mapping(expression = "java(orderDetail.getOrder().getId())", target = "orderId")
    @Mapping(expression = "java(orderDetail.getProduct().getId())", target = "productId")
    @Mapping(expression = "java(orderDetail.getProduct().getImageUrl())", target = "productImageUrl")
    @Mapping(expression = "java(orderDetail.getProduct().getName())", target = "productName")
    @Mapping(expression = "java(orderDetail.getProduct().getSubCategory().getId())", target = "subCategoryID")
    @Mapping(expression = "java(orderDetail.getProduct().getSubCategory().getName())", target = "subCategoryName")
    OrderDetailViewDTO toDto(OrderDetail orderDetail);

    default OrderDetail fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(id);
        return orderDetail;
    }
}
