package thi.app.web.errors;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletException;
import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(AccountSessionTimeOutException.class)
    public ResponseEntity<ErrorMessage> accountSessionTimeOutException(AccountSessionTimeOutException ex, WebRequest request) {
        return setResponseErrorMessage(ex,request,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorMessage> accountNotFoundException(AccountNotFoundException ex, WebRequest request) {
        return setResponseErrorMessage(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountNotActivatedException.class)
    public ResponseEntity<ErrorMessage> accountNotActivatedException(AccountNotActivatedException ex, WebRequest request) {
        return setResponseErrorMessage(ex, request, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorMessage> usernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        return setResponseErrorMessage(ex,request,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorMessage> badCredentialsException(BadCredentialsException ex, WebRequest request) {
        return setResponseErrorMessage(ex,request,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ErrorMessage> disabledException(DisabledException ex, WebRequest request) {
        return setResponseErrorMessage(ex,request,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> productAlreadyExistsException(ProductAlreadyExistsException ex, WebRequest request) {
        return setResponseErrorMessage(ex,request,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> productNotFoundException(ProductNotFoundException ex, WebRequest request) {
        return setResponseErrorMessage(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorMessage> orderNotFoundException(OrderNotFoundException ex, WebRequest request) {
        return setResponseErrorMessage(ex, request, HttpStatus.BAD_REQUEST);
    }

        @ExceptionHandler(ServletException.class)
    public ResponseEntity<ErrorMessage> expiredJwtException(ServletException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.UNAUTHORIZED);
//        return setResponseErrorMessage(ex,request,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                "Something went wrong!",
                request.getDescription(false));
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorMessage> setResponseErrorMessage(Exception ex, WebRequest request, HttpStatus status) {
        ErrorMessage message = new ErrorMessage(
                status.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<ErrorMessage>(message, status);
    }
}