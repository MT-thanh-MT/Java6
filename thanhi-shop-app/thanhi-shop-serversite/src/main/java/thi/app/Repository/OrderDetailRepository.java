package thi.app.Repository;

import org.springframework.stereotype.Repository;
import thi.app.model.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends SearchRepository<OrderDetail, Long> {
}