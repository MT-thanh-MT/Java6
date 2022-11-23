package thi.app.web.errors;

import org.springframework.security.core.AuthenticationException;

public class AccountSessionTimeOutException extends AuthenticationException {
    private static final Long serialVersionUID = 1L;

    public AccountSessionTimeOutException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AccountSessionTimeOutException(String msg) {
        super(msg);
    }
}
