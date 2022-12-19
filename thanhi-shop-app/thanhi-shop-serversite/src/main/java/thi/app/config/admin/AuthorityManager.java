package thi.app.config.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thi.app.model.dto.AccountDTO;
import thi.app.service.IAccountService;

@CrossOrigin
@RestController
@RequestMapping("/admin/authority")
public class AuthorityManager {
    @Autowired
    IAccountService accountService;

    @PutMapping
    public ResponseEntity<AccountDTO> updateAuthority(@RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok().body(accountService.update(accountDTO));
    }
}
