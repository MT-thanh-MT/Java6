package thi.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thi.app.Repository.ProductRepository;
import thi.app.model.dto.ProductDTO;
import thi.app.model.mapper.ProductMapper;
import thi.app.service.IProductService;

import java.util.List;

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
}
