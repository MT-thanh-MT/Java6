package thi.app.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import thi.app.model.dto.SubCategoryDTO;
import thi.app.model.entity.SubCategory;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface SubCategoryMapper extends EntityMapper<SubCategoryDTO, SubCategory>{

    @Mapping(source = "cateId", target = "category")
    SubCategory toEntity(SubCategoryDTO dto);

    @Mapping(source = "category.id", target = "cateId")
    SubCategoryDTO toDto(SubCategory entity);

    default SubCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        SubCategory subCategory = new SubCategory();
        subCategory.setId(id);
        return subCategory;
    }
}
