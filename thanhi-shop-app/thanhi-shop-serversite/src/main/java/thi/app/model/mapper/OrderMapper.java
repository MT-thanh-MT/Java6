package thi.app.model.mapper;

import org.springframework.stereotype.Service;
import thi.app.model.dto.OrderDTO;
import thi.app.model.entity.Order;

@Service
public interface OrderMapper extends EntityMapper<OrderDTO, Order>{
}
