package thi.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thi.app.Repository.ProductRepository;
import thi.app.model.dto.ProductDTO;
import thi.app.model.entity.Product;
import thi.app.model.mapper.ProductMapper;
import thi.app.service.IProductService;
import thi.app.web.errors.ProductAlreadyExistsException;
import thi.app.web.errors.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductRepository productRepo;

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<ProductDTO> getAll() {
        return productMapper.toDto(productRepo.findAll());
    }

    @Override
    public List<ProductDTO> getByCategory(Long cid) {
        return productMapper.toDto(productRepo.getProductByCategoryId(cid));
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        if (productDTO.getId() != null) {
            throw new ProductAlreadyExistsException("This product already exists! check product id and try again.");
        } else {
            Product product = productMapper.toEntity(productDTO);
            product = productRepo.save(product);
            return productMapper.toDto(product);
        }
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {
        if (productDTO.getId() == null || productDTO.getId() <= 0) {
            throw new ProductAlreadyExistsException("This product not exists! check product id and try again.");
        } else {
            Product product = productMapper.toEntity(productDTO);
            product = productRepo.save(product);
            return productMapper.toDto(product);
        }
    }

    @Override
    public boolean delete(Long id) {
        Product product = productRepo.findById(id).orElse(null);
        if (product == null) {
            throw new ProductAlreadyExistsException("This product not exists! check product id and try again.");
        } else {
            productRepo.delete(product);
            return true;
        }
    }

    @Override
    public List<ProductDTO> getProductByNameOrId(Optional<String> key) {
        List<Product> list = new ArrayList<>();
        if (key.isPresent()){
            list = productRepo.findProductsByNameLikeOrIdLike(key.get().toLowerCase());
        } else if (key.get().isEmpty()){
            list = productRepo.findAll();
        }
        return productMapper.toDto(list);
    }

    @Override
    public List<ProductDTO> getBySubCategory(Long scid) {
        return productMapper.toDto(productRepo.getProductBySubCategoryId(scid));
    }

    @Override
    public ProductDTO getProductById(Long id) {
        ProductDTO product = productMapper.toDto(productRepo.findById(id).orElse(null));
        if (product == null) {
            throw new ProductNotFoundException("This product was not found!");
        } else {
            return product;
        }
    }
}
