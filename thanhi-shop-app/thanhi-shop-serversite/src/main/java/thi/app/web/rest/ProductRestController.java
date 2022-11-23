package thi.app.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thi.app.model.dto.ProductDTO;
import thi.app.service.IProductService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/product")
public class ProductRestController {

    @Autowired
    IProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(@RequestParam(value = "cid", defaultValue = "0") Optional<Long> cid) {
        List<ProductDTO> list = null;
        if (cid.isPresent() && cid.get() > 0) {
            list = this.productService.getByCategory(cid.get());
            return ResponseEntity.ok().body(list);
        } else {
            list = this.productService.getAll();
            return ResponseEntity.ok().body(list);
        }
    }
}
