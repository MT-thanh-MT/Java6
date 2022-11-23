package thi.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thi.app.Repository.CategoryRepository;
import thi.app.model.dto.CategoryDTO;
import thi.app.model.mapper.CategoryMapper;
import thi.app.service.ICategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> findAll() {
        return categoryMapper.toDto(categoryRepository.findAll());
    }
}
