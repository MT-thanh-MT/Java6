package thi.app.model.mapper;

import org.springframework.stereotype.Service;
import thi.app.model.dto.CategoryDTO;
import thi.app.model.entity.Category;

@Service
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category>{
}
