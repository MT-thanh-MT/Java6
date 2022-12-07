package thi.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import thi.app.Repository.AccountRepository;
import thi.app.model.dto.AccountDTO;
import thi.app.model.entity.Account;
import thi.app.model.mapper.AccountMapper;
import thi.app.service.IAccountService;
import thi.app.web.errors.AccountNotFoundException;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Account createAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    public List<AccountDTO> getAll() {
        return accountMapper.toDto(accountRepository.findAll());
    }

    @Override
    public AccountDTO update(AccountDTO accountDTO) {
        Account account = accountMapper.toEntity(accountDTO);
        accountDTO = accountMapper.toDto(accountRepository.save(account));
        return accountDTO;
    }

    @Override
    public boolean delete(Long id) {
        Account account = this.accountRepository.findById(id).orElse(null);
        if (account == null){
            throw new AccountNotFoundException("This account not exists! check account id and try again.");
        } else {
            accountRepository.delete(account);
            return true;
        }
    }
}
