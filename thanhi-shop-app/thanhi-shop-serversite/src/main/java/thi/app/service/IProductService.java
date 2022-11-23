package thi.app.service;

import thi.app.model.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> getAll();
    List<ProductDTO> getByCategory(Long cid);
}
