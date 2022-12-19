package thi.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import thi.app.Repository.AccountRepository;
import thi.app.model.entity.Account;
import thi.app.model.entity.Role;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class ThanhiShopServersiteApplicationTests {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {

    }

}
