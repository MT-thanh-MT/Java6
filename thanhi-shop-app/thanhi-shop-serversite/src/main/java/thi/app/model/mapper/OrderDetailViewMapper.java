package thi.app.model.mapper;

import org.springframework.stereotype.Service;
import thi.app.model.dto.OrderDetailDTO;
import thi.app.model.dto.OrderDetailViewDTO;
import thi.app.model.entity.OrderDetail;

@Service
public interface OrderDetailViewMapper extends EntityMapper<OrderDetailViewDTO, OrderDetail>{
}
