package thi.app.model.mapper;

import org.springframework.stereotype.Service;
import thi.app.model.dto.OrderDTO;
import thi.app.model.dto.OrderViewDTO;
import thi.app.model.entity.Order;

@Service
public interface OrderViewMapper extends EntityMapper<OrderViewDTO, Order>{
}
