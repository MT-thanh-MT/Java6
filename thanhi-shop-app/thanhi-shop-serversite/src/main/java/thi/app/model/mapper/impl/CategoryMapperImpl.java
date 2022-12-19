package thi.app.model.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thi.app.model.dto.CategoryDTO;
import thi.app.model.entity.Category;
import thi.app.model.mapper.CategoryMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//@Service
public class CategoryMapperImpl implements CategoryMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Category toEntity(CategoryDTO dto) {
        Category category = modelMapper.map(dto, Category.class);
        return category;
    }

    @Override
    public CategoryDTO toDto(Category entity) {
        CategoryDTO categoryDTO = modelMapper.map(entity, CategoryDTO.class);
        return categoryDTO;
    }

    @Override
    public List<Category> toEntity(List<CategoryDTO> dtoList) {
        return dtoList.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> toDto(List<Category> entityList) {
        return entityList.stream()
                .filter(Objects::nonNull)
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
