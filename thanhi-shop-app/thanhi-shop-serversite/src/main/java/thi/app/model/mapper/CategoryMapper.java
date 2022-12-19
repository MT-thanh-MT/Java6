package thi.app.model.mapper;

import org.mapstruct.Mapper;
import thi.app.model.dto.CategoryDTO;
import thi.app.model.entity.Category;

@Mapper(componentModel = "spring", uses = {})
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category>{

    default Category fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }

}
