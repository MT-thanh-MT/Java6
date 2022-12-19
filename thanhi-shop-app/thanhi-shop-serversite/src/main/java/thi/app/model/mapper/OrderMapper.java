package thi.app.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import thi.app.model.dto.OrderDTO;
import thi.app.model.entity.Order;

@Mapper(componentModel = "spring", uses = {OrderDetailMapper.class, AccountMapper.class})
public interface OrderMapper extends EntityMapper<OrderDTO, Order>{

    @Mapping(source = "accountId", target = "account")
    Order toEntity(OrderDTO dto);

    @Mapping(source = "account.id", target = "accountId")
    OrderDTO toDto(Order entity);
    default Order fromId(Long id) {
        if (id == null) {
            return null;
        }
        Order order = new Order();
        order.setId(id);
        return order;
    }

}
