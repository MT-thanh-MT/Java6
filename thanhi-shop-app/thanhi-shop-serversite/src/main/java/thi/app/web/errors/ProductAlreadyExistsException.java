package thi.app.web.errors;

import org.springframework.security.core.AuthenticationException;

public class ProductAlreadyExistsException extends AuthenticationException {

    private static final Long serialVersionUID = 1L;

    public ProductAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ProductAlreadyExistsException(String msg) {
        super(msg);
    }
}
