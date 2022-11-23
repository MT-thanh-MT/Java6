package thi.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import thi.app.Repository.AccountRepository;
import thi.app.model.dto.AccountDTO;
import thi.app.model.entity.Account;
import thi.app.model.jwt.JwtRequest;
import thi.app.model.jwt.JwtRespone;
import thi.app.model.mapper.AccountMapper;
import thi.app.utils.JwtUtil;
import thi.app.web.errors.AccountNotActivatedException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    AccountRepository accountRepo;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    public JwtRespone createJwtToken(JwtRequest jwtRequest) throws Exception {
        String username = jwtRequest.getUsername();
        String password = jwtRequest.getPassword();


        final UserDetails userDetails = loadUserByUsername(username);

        authenticate(username, password);

        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        Account account = accountRepo.findByUsername(username).get();
        AccountDTO accountDTO = accountMapper.toDto(account);
        return new JwtRespone(accountDTO, newGeneratedToken);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountByUsernameFromDB = accountRepo.findByUsername(username);
        return accountByUsernameFromDB.map(account -> createUserDetail(account))
                .orElseThrow(() -> {throw new UsernameNotFoundException("Account: " + username
                                                                    +" was not found in the database!");});
    }

    private User createUserDetail(Account account) {
        if (!account.isActivated()) {
            throw new AccountNotActivatedException("Account: " + account.getUsername() + " was not activated!");
        }

        List<GrantedAuthority> grantedAuthorities = account.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        return new User(account.getUsername(), account.getPassword(), grantedAuthorities);
    }

    private void authenticate(String username, String password) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("User is disabled");
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Bad credentials from user");
        }
    }
}
