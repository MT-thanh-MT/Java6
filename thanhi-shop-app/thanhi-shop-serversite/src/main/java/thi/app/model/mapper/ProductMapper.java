package thi.app.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import thi.app.model.dto.ProductDTO;
import thi.app.model.entity.Product;

@Mapper(componentModel = "spring", uses = {StatusMapper.class, SubCategoryMapper.class})
public interface ProductMapper extends EntityMapper<ProductDTO, Product>{

    @Mapping(source = "status", target = "status")
    @Mapping(source = "subCateId", target = "subCategory")
    Product toEntity(ProductDTO dto);

    @Mapping(source = "status.name", target = "status")
    @Mapping(source = "subCategory.id", target = "subCateId")
    ProductDTO toDto(Product entity);
    default Product fromId(Long id) {
        if (id == null) {
            return null;
        }
        Product product = new Product();
        product.setId(id);
        return product;
    }

}
