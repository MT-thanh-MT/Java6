package thi.app.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thi.app.Repository.AccountRepository;
import thi.app.Repository.ProductRepository;
import thi.app.model.dto.AccountDTO;
import thi.app.model.dto.ProductDTO;
import thi.app.model.entity.Account;
import thi.app.model.mapper.AccountMapper;
import thi.app.model.mapper.ProductMapper;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/test")
public class RoleRestController {

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    AccountRepository ar;

    @Autowired
    ProductRepository pr;

    @Autowired
    ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAcconunt() {
        List<AccountDTO> list = accountMapper.toDto(ar.findAll());
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<List<Account>> getEntity(@RequestBody List<AccountDTO> accountDTO) {
        List<Account> list = accountMapper.toEntity(accountDTO);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("product")
    public ResponseEntity<List<ProductDTO>> getAllProduct() {
        List<ProductDTO> list = productMapper.toDto(this.pr.findAll());
        return ResponseEntity.ok().body(list);
    }
}
