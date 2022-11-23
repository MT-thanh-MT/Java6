package thi.app.model.mapper;

import org.springframework.stereotype.Service;
import thi.app.model.dto.SubCategoryDTO;
import thi.app.model.entity.SubCategory;

@Service
public interface SubCategoryMapper extends EntityMapper<SubCategoryDTO, SubCategory>{
}
