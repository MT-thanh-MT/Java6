package thi.app.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import thi.app.Repository.AccountRepository;
import thi.app.model.dto.AccountDTO;
import thi.app.model.entity.Account;
import thi.app.model.mapper.AccountMapper;
import thi.app.web.errors.AccountNotFoundException;

@ContextConfiguration(classes = {AccountServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AccountServiceImplTest {
    @MockBean
    private AccountMapper accountMapper;

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @MockBean
    private PasswordEncoder passwordEncoder;

    /**
     * Method under test: {@link AccountServiceImpl#createAccount(Account)}
     */
    @Test
    void testCreateAccount() {
        Account account = new Account();
        account.setActivated(true);
        account.setCreateBy("Create By");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        account.setCreateDate(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        account.setEmail("jane.doe@example.org");
        account.setFirstName("Jane");
        account.setId(123L);
        account.setLastName("Doe");
        account.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        account.setModifiedDate(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        account.setPassword("iloveyou");
        account.setRoles(new HashSet<>());
        account.setUsername("janedoe");
        when(accountRepository.save((Account) any())).thenReturn(account);
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        Account account1 = new Account();
        account1.setActivated(true);
        account1.setCreateBy("Create By");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        account1.setCreateDate(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        account1.setEmail("jane.doe@example.org");
        account1.setFirstName("Jane");
        account1.setId(123L);
        account1.setLastName("Doe");
        account1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        account1.setModifiedDate(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        account1.setPassword("iloveyou");
        account1.setRoles(new HashSet<>());
        account1.setUsername("janedoe");
        assertSame(account, accountServiceImpl.createAccount(account1));
        verify(accountRepository).save((Account) any());
        verify(passwordEncoder).encode((CharSequence) any());
        assertEquals("secret", account1.getPassword());
    }

    /**
     * Method under test: {@link AccountServiceImpl#createAccount(Account)}
     */
    @Test
    void testCreateAccount2() {
        Account account = new Account();
        account.setActivated(true);
        account.setCreateBy("Create By");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        account.setCreateDate(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        account.setEmail("jane.doe@example.org");
        account.setFirstName("Jane");
        account.setId(123L);
        account.setLastName("Doe");
        account.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        account.setModifiedDate(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        account.setPassword("iloveyou");
        account.setRoles(new HashSet<>());
        account.setUsername("janedoe");
        when(accountRepository.save((Account) any())).thenReturn(account);
        when(passwordEncoder.encode((CharSequence) any())).thenThrow(new AccountNotFoundException("Msg"));

        Account account1 = new Account();
        account1.setActivated(true);
        account1.setCreateBy("Create By");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        account1.setCreateDate(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        account1.setEmail("jane.doe@example.org");
        account1.setFirstName("Jane");
        account1.setId(123L);
        account1.setLastName("Doe");
        account1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        account1.setModifiedDate(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        account1.setPassword("iloveyou");
        account1.setRoles(new HashSet<>());
        account1.setUsername("janedoe");
        assertThrows(AccountNotFoundException.class, () -> accountServiceImpl.createAccount(account1));
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link AccountServiceImpl#getAll()}
     */
    @Test
    void testGetAll() {
        ArrayList<AccountDTO> accountDTOList = new ArrayList<>();
        when(accountMapper.toDto((List<Account>) any())).thenReturn(accountDTOList);
        when(accountRepository.findAll()).thenReturn(new ArrayList<>());
        List<AccountDTO> actualAll = accountServiceImpl.getAll();
        assertSame(accountDTOList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(accountMapper).toDto((List<Account>) any());
        verify(accountRepository).findAll();
    }

    /**
     * Method under test: {@link AccountServiceImpl#getAll()}
     */
    @Test
    void testGetAll2() {
        when(accountMapper.toDto((List<Account>) any())).thenReturn(new ArrayList<>());
        when(accountRepository.findAll()).thenThrow(new AccountNotFoundException("Msg"));
        assertThrows(AccountNotFoundException.class, () -> accountServiceImpl.getAll());
        verify(accountRepository).findAll();
    }

    /**
     * Method under test: {@link AccountServiceImpl#update(AccountDTO)}
     */
    @Test
    void testUpdate() {
        Account account = new Account();
        account.setActivated(true);
        account.setCreateBy("Create By");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        account.setCreateDate(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        account.setEmail("jane.doe@example.org");
        account.setFirstName("Jane");
        account.setId(123L);
        account.setLastName("Doe");
        account.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        account.setModifiedDate(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        account.setPassword("iloveyou");
        account.setRoles(new HashSet<>());
        account.setUsername("janedoe");
        AccountDTO accountDTO = new AccountDTO();
        when(accountMapper.toDto((Account) any())).thenReturn(accountDTO);
        when(accountMapper.toEntity((AccountDTO) any())).thenReturn(account);

        Account account1 = new Account();
        account1.setActivated(true);
        account1.setCreateBy("Create By");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        account1.setCreateDate(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        account1.setEmail("jane.doe@example.org");
        account1.setFirstName("Jane");
        account1.setId(123L);
        account1.setLastName("Doe");
        account1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        account1.setModifiedDate(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        account1.setPassword("iloveyou");
        account1.setRoles(new HashSet<>());
        account1.setUsername("janedoe");
        when(accountRepository.save((Account) any())).thenReturn(account1);
        assertSame(accountDTO, accountServiceImpl.update(new AccountDTO()));
        verify(accountMapper).toDto((Account) any());
        verify(accountMapper).toEntity((AccountDTO) any());
        verify(accountRepository).save((Account) any());
    }

    /**
     * Method under test: {@link AccountServiceImpl#update(AccountDTO)}
     */
    @Test
    void testUpdate2() {
        Account account = new Account();
        account.setActivated(true);
        account.setCreateBy("Create By");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        account.setCreateDate(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        account.setEmail("jane.doe@example.org");
        account.setFirstName("Jane");
        account.setId(123L);
        account.setLastName("Doe");
        account.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        account.setModifiedDate(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        account.setPassword("iloveyou");
        account.setRoles(new HashSet<>());
        account.setUsername("janedoe");
        when(accountMapper.toDto((Account) any())).thenReturn(new AccountDTO());
        when(accountMapper.toEntity((AccountDTO) any())).thenReturn(account);
        when(accountRepository.save((Account) any())).thenThrow(new AccountNotFoundException("Msg"));
        assertThrows(AccountNotFoundException.class, () -> accountServiceImpl.update(new AccountDTO()));
        verify(accountMapper).toEntity((AccountDTO) any());
        verify(accountRepository).save((Account) any());
    }

    /**
     * Method under test: {@link AccountServiceImpl#delete(Long)}
     */
    @Test
    void testDelete() {
        Account account = new Account();
        account.setActivated(true);
        account.setCreateBy("Create By");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        account.setCreateDate(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        account.setEmail("jane.doe@example.org");
        account.setFirstName("Jane");
        account.setId(123L);
        account.setLastName("Doe");
        account.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        account.setModifiedDate(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        account.setPassword("iloveyou");
        account.setRoles(new HashSet<>());
        account.setUsername("janedoe");
        Optional<Account> ofResult = Optional.of(account);
        doNothing().when(accountRepository).delete((Account) any());
        when(accountRepository.findById((Long) any())).thenReturn(ofResult);
        assertTrue(accountServiceImpl.delete(123L));
        verify(accountRepository).findById((Long) any());
        verify(accountRepository).delete((Account) any());
    }

    /**
     * Method under test: {@link AccountServiceImpl#delete(Long)}
     */
    @Test
    void testDelete2() {
        Account account = new Account();
        account.setActivated(true);
        account.setCreateBy("Create By");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        account.setCreateDate(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        account.setEmail("jane.doe@example.org");
        account.setFirstName("Jane");
        account.setId(123L);
        account.setLastName("Doe");
        account.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        account.setModifiedDate(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        account.setPassword("iloveyou");
        account.setRoles(new HashSet<>());
        account.setUsername("janedoe");
        Optional<Account> ofResult = Optional.of(account);
        doThrow(new AccountNotFoundException("Msg")).when(accountRepository).delete((Account) any());
        when(accountRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(AccountNotFoundException.class, () -> accountServiceImpl.delete(123L));
        verify(accountRepository).findById((Long) any());
        verify(accountRepository).delete((Account) any());
    }

    /**
     * Method under test: {@link AccountServiceImpl#delete(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDelete3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElse(Object)" because the return value of "thi.app.Repository.AccountRepository.findById(Object)" is null
        //       at thi.app.service.impl.AccountServiceImpl.delete(AccountServiceImpl.java:48)
        //   See https://diff.blue/R013 to resolve this issue.

        doNothing().when(accountRepository).delete((Account) any());
        when(accountRepository.findById((Long) any())).thenReturn(null);
        accountServiceImpl.delete(123L);
    }

    /**
     * Method under test: {@link AccountServiceImpl#delete(Long)}
     */
    @Test
    void testDelete4() {
        doNothing().when(accountRepository).delete((Account) any());
        when(accountRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(AccountNotFoundException.class, () -> accountServiceImpl.delete(123L));
        verify(accountRepository).findById((Long) any());
    }
}

