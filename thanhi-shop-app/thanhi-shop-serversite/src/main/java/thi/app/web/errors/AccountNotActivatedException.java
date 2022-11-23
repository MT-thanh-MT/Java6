package thi.app.web.errors;

import org.springframework.security.core.AuthenticationException;

public class AccountNotActivatedException extends AuthenticationException {
    private static final Long serialVersionUID = 1L;

    public AccountNotActivatedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AccountNotActivatedException(String msg) {
        super(msg);
    }
}
