package thi.app.web.errors;

import org.springframework.security.core.AuthenticationException;

public class ProductNotFoundException extends AuthenticationException {

    private static final Long serialVersionUID = 1L;

    public ProductNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ProductNotFoundException(String msg) {
        super(msg);
    }
}
