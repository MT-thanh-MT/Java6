package thi.app.service;

import org.springframework.stereotype.Service;
import thi.app.model.entity.Account;


public interface IAccountService {
    public Account createAccount(Account account);
}
