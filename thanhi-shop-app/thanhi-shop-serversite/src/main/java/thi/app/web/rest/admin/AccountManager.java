package thi.app.web.rest.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thi.app.model.dto.AccountDTO;
import thi.app.model.entity.Account;
import thi.app.model.mapper.AccountMapper;
import thi.app.service.IAccountService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/admin/account")
public class AccountManager {

    @Autowired
    IAccountService accountService;

    @Autowired
    AccountMapper accountMapper;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAll() {
        List<AccountDTO> list = accountService.getAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO accDto) {
        Account account = accountMapper.toEntity(accDto);
        accDto = accountMapper.toDto(accountService.createAccount(account));
        return ResponseEntity.ok().body(accDto);
    }

    @PutMapping
    public ResponseEntity<AccountDTO> updateAccount(@Valid @RequestBody AccountDTO accDto) {
        return ResponseEntity.ok().body(accountService.update(accDto));
    }

    @DeleteMapping
    public ResponseEntity deleteAccount(@RequestParam(value = "id", defaultValue = "0") Optional<Long> id) {
        if (!id.isPresent()) {
            throw new RuntimeException("ID is null!");
        }
        if (accountService.delete(id.get())) {
            return ResponseEntity.ok().build();
        } else {
            throw new RuntimeException("Can`t delete this account!");
        }
    }
}
