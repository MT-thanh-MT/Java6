package thi.app.model.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thi.app.model.dto.SubCategoryDTO;
import thi.app.model.entity.Category;
import thi.app.model.entity.SubCategory;
import thi.app.model.mapper.SubCategoryMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//@Service
public class SubCategoryMapperImpl implements SubCategoryMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SubCategory toEntity(SubCategoryDTO dto) {
        SubCategory subCategory = modelMapper.map(dto, SubCategory.class);
        Category category = new Category();
        category.setId(dto.getCateId());
        subCategory.setCategory(category);
        return subCategory;
    }

    @Override
    public SubCategoryDTO toDto(SubCategory entity) {
        SubCategoryDTO subCategoryDTO = modelMapper.map(entity, SubCategoryDTO.class);
        subCategoryDTO.setCateId(entity.getCategory().getId());
        return subCategoryDTO;
    }

    @Override
    public List<SubCategory> toEntity(List<SubCategoryDTO> dtoList) {
        return dtoList.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<SubCategoryDTO> toDto(List<SubCategory> entityList) {
        return entityList.stream()
                .filter(Objects::nonNull)
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
