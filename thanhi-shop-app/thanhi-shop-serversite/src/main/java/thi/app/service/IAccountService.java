package thi.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import thi.app.model.dto.AccountDTO;
import thi.app.model.entity.Account;

import java.util.List;


public interface IAccountService {
    public Account createAccount(Account account);

    public List<AccountDTO> getAll();

    public AccountDTO update(AccountDTO accountDTO);

    public boolean delete(Long id);
}
