package thi.app.config.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import thi.app.model.dto.ProductDTO;
import thi.app.model.dto.SearchRequestDTO;
import thi.app.service.IProductService;
import thi.app.web.errors.ProductAlreadyExistsException;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/admin/product")
public class ProductManagent {

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

    @PostMapping("/reindex")
    public ResponseEntity<Void> reindexProduct() throws InterruptedException {
        productService.indexData();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        List<ProductDTO> list = productService.getAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{key}")
    public ResponseEntity<List<ProductDTO>> getByNameOrId(@PathVariable(value = "key") Optional<String> key) {
        List<ProductDTO> list = productService.getProductByNameOrId(key);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        productDTO.setCreateBy(username);
        ProductDTO newProduct = productService.create(productDTO);
        return ResponseEntity.ok().body(newProduct);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        productDTO.setModifiedBy(username);
        productDTO.setModifiedDate(Instant.now());
        ProductDTO newProduct = productService.update(productDTO);
        return ResponseEntity.ok().body(newProduct);
    }

    @DeleteMapping
    public ResponseEntity deleteProduct(@RequestParam(value = "id", defaultValue = "0") Optional<Long> id) {
        if (!id.isPresent()) {
            throw new ProductAlreadyExistsException("ID is null!");
        }
        if (productService.delete(id.get())) {
            return ResponseEntity.ok().build();
        } else {
            throw new ProductAlreadyExistsException("Can`t delete product!");
        }
    }
}
