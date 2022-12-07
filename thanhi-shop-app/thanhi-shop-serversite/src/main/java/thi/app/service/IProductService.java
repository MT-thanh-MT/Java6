package thi.app.service;

import thi.app.model.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<ProductDTO> getAll();

    List<ProductDTO> getByCategory(Long cid);

    ProductDTO create(ProductDTO productDTO);

    ProductDTO update(ProductDTO productDTO);

    boolean delete(Long id);

    List<ProductDTO> getProductByNameOrId(Optional<String> key);

    ProductDTO getProductById(Long aLong);

    List<ProductDTO> getBySubCategory(Long aLong);
}
