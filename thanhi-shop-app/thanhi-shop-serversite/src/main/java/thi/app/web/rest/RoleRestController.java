package thi.app.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thi.app.Repository.AccountRepository;
import thi.app.Repository.ProductRepository;
import thi.app.Repository.RoleRepository;
import thi.app.model.dto.AccountDTO;
import thi.app.model.dto.ProductDTO;
import thi.app.model.dto.RoleDTO;
import thi.app.model.entity.Account;
import thi.app.model.mapper.AccountMapper;
import thi.app.model.mapper.ProductMapper;
import thi.app.model.mapper.RoleMapper;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/role")
public class RoleRestController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleMapper roleMapper;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAll() {
        List<RoleDTO> list = roleMapper.toDto(roleRepository.findAll());
        return ResponseEntity.ok().body(list);
    }
}
