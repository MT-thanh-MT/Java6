package thi.app.model.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thi.app.model.dto.ProductDTO;
import thi.app.model.entity.Product;
import thi.app.model.entity.Status;
import thi.app.model.entity.SubCategory;
import thi.app.model.mapper.ProductMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//@Service
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Product toEntity(ProductDTO dto) {
        Product product = modelMapper.map(dto, Product.class);
        Status status = new Status(dto.getStatus(), null);
        product.setStatus(status);
        SubCategory subCategory = new SubCategory();
        subCategory.setId(dto.getSubCateId());
        product.setSubCategory(subCategory);
        return product;
    }

    @Override
    public ProductDTO toDto(Product entity) {
        ProductDTO productDTO = modelMapper.map(entity, ProductDTO.class);
        productDTO.setStatus(entity.getStatus().getName());
        productDTO.setSubCateId(entity.getSubCategory().getId());
        return productDTO;
    }

    @Override
    public List<Product> toEntity(List<ProductDTO> dtoList) {
        return dtoList.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> toDto(List<Product> entityList) {
        return entityList.stream()
                .filter(Objects::nonNull)
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
