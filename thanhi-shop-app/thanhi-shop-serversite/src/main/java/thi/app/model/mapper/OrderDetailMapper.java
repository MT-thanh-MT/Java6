package thi.app.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import thi.app.model.dto.OrderDetailDTO;
import thi.app.model.entity.OrderDetail;

@Mapper(componentModel = "spring", uses = {OrderMapper.class, ProductMapper.class})
public interface OrderDetailMapper extends EntityMapper<OrderDetailDTO, OrderDetail>{

    @Mapping(source = "orderId", target = "order")
    @Mapping(source = "productId", target = "product")
    OrderDetail toEntity(OrderDetailDTO dto);

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "product.id", target = "productId")
    OrderDetailDTO toDto(OrderDetail entity);
    default OrderDetail fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(id);
        return orderDetail;
    }

}
