package thi.app.web.errors;

import org.springframework.security.core.AuthenticationException;

public class OrderNotFoundException extends AuthenticationException {
    private static final Long serialVersionUID = 1L;

    public OrderNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public OrderNotFoundException(String msg) {
        super(msg);
    }
}
