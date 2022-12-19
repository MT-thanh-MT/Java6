package thi.app.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thi.app.model.dto.ProductDTO;
import thi.app.model.dto.SearchRequestDTO;
import thi.app.service.IProductService;
import thi.app.web.errors.ProductNotFoundException;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/product")
public class ProductRestController {

    @Autowired
    IProductService productService;

    @PostMapping("search")
    public ResponseEntity<List<ProductDTO>> searchProduct(@RequestBody SearchRequestDTO searchRequestDTO) {
        List<ProductDTO> list = productService.searchProduct(
                searchRequestDTO.getText(),
                searchRequestDTO.getFields(),
                searchRequestDTO.getLimit()
        );
        System.out.println(list);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@RequestParam(value = "cid", defaultValue = "0") Optional<Long> cid) {
        List<ProductDTO> list = null;
        if (cid.isPresent() && cid.get() > 0) {
            list = this.productService.getByCategory(cid.get());
            return ResponseEntity.ok().body(list);
        } else {
            list = this.productService.getAll();
            return ResponseEntity.ok().body(list);
        }
    }

    @GetMapping("/subcategory")
    public ResponseEntity<List<ProductDTO>> getProductsBySubCategory(@RequestParam(value = "scid", defaultValue = "0") Optional<Long> scid) {
        List<ProductDTO> list = null;
        if (scid.isPresent() && scid.get() > 0) {
            list = this.productService.getBySubCategory(scid.get());
            return ResponseEntity.ok().body(list);
        } else {
            return ResponseEntity.ok().body(list);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getOneProductById(@PathVariable Optional<Long> id) {
        if (!id.isPresent()) {
            throw new ProductNotFoundException("Id is null. Please check again");
        } else {
            ProductDTO product = productService.getProductById(id.get());
            return ResponseEntity.ok().body(product);
        }
    }
}
