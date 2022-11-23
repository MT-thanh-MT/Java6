package thi.app.model.mapper;

import org.springframework.stereotype.Service;
import thi.app.model.dto.ProductDTO;
import thi.app.model.entity.Product;

@Service
public interface ProductMapper extends EntityMapper<ProductDTO, Product>{
}
