package thi.app.web.errors;

import org.springframework.security.core.AuthenticationException;

public class AccountNotFoundException extends AuthenticationException {
    private static final Long serialVersionUID = 1L;

    public AccountNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AccountNotFoundException(String msg) {
        super(msg);
    }
}
