package thi.app.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import thi.app.model.dto.OrderViewDTO;
import thi.app.model.entity.Order;

@Mapper(componentModel = "spring", uses = {OrderDetailViewMapper.class, AccountMapper.class, StatusMapper.class})
public interface OrderViewMapper extends EntityMapper<OrderViewDTO, Order>{

    @Mapping(source = "accountId", target = "account")
    @Mapping(source = "status", target = "status")
    Order toEntity(OrderViewDTO orderViewDTO);

    @Mapping(expression = "java(order.getAccount().getId())", target = "accountId")
    @Mapping(expression = "java(order.getAccount().getUsername())", target = "payer")
    @Mapping(expression = "java(order.getStatus().getName())", target = "status")
    @Mapping(source = "orderDetails", target = "orderDetailView")
    OrderViewDTO toDto(Order order);

    default Order fromId(Long id) {
        if (id == null) {
            return null;
        }
        Order odOrder = new Order();
        odOrder.setId(id);
        return odOrder;
    }
}
