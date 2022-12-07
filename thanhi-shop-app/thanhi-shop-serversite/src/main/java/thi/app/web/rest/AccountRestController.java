package thi.app.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thi.app.model.dto.AccountDTO;
import thi.app.model.entity.Account;
import thi.app.model.mapper.AccountMapper;
import thi.app.service.IAccountService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/account")
public class AccountRestController {

    @Autowired
    IAccountService accountService;

    @Autowired
    AccountMapper accountMapper;

    @PostMapping("/sing-up")
    public ResponseEntity<AccountDTO> singUp(@Valid  @RequestBody AccountDTO accDto) {
        Account account = accountMapper.toEntity(accDto);
        accDto = accountMapper.toDto(accountService.createAccount(account));
        return ResponseEntity.ok().body(accDto);
    }
}
