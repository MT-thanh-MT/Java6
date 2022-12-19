package thi.app.Repository;

import org.springframework.stereotype.Repository;
import thi.app.model.entity.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends SearchRepository<Account, Long> {
    public Optional<Account> findByUsername(String username);
}