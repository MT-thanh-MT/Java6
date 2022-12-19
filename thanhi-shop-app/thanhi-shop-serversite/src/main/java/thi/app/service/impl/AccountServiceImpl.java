package thi.app.service.impl;

import joptsimple.internal.Strings;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.Arrays;
import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceUnit
    private EntityManagerFactory emf;

    private static final List<String> SEARCHABLE_FIELDS = Arrays.asList("username", "firstName", "lastName", "email");

    @Override
    public List<Account> searchAccount(String text, List<String> fields, int limit) {

        List<String> fieldsToSearchBy = fields.isEmpty() ? SEARCHABLE_FIELDS : fields;

        boolean containsInvalidField = fieldsToSearchBy.stream().anyMatch(f -> !SEARCHABLE_FIELDS.contains(f));

        if (containsInvalidField) {
            throw new IllegalArgumentException();
        }


        if (Strings.isNullOrEmpty(text)) {
            return accountRepository.findAll();
        } else {
            return accountRepository.searchBy(text, limit, fields.toArray(new String[0]));
        }
    }

    @Override
    public void indexData() throws InterruptedException {
        EntityManager em = emf.createEntityManager();
        SearchSession searchSession = Search.session(em);
        MassIndexer indexer = searchSession.massIndexer(Account.class)
                .threadsToLoadObjects(7);
        indexer.startAndWait();
    }

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
        if (account == null) {
            throw new AccountNotFoundException("This account not exists! check account id and try again.");
        } else {
            accountRepository.delete(account);
            return true;
        }
    }
}
