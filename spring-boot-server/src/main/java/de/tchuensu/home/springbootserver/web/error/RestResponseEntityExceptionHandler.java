package de.tchuensu.home.springbootserver.web.error;

import de.tchuensu.home.springbootserver.web.exception.*;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public RestResponseEntityExceptionHandler() {
        super();
    }

    // API

    // 400 BAD_REQUEST

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
        return handleExceptionInternal(ex, " ConstraintViolationException \n" + ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
        return handleExceptionInternal(ex, " DataIntegrityViolationException \n" + ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return handleExceptionInternal(ex, " HttpMessageNotReadableException \n" + ex.getMessage(), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

        return handleExceptionInternal(ex, " MethodArgumentNotValidException \n" + ex.getMessage(), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, " MissingPathVariableException" + ex.getMessage() ,headers, HttpStatus.BAD_REQUEST, request);
    }

    // Rodrigue Tchuensu P.
    // DuplicateEntityException handling
    @ExceptionHandler(value = {DuplicateEntityException.class})
    protected ResponseEntity<Object> handleDuplicatEntity(final RuntimeException ex, final WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }



    //Rodrigue Tchuensu P. Contribution
    //401 Unauthorized
    @ExceptionHandler(value = {UnauthorizedException.class, UserCredentialException.class})
    protected ResponseEntity<Object> handleUnauthorized(final RuntimeException ex, final WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    // 402 Payment Required - This code is reserved for future use.

    // Rodrigue Tchuensu P. Contribution
    //403 Forbidden
    @ExceptionHandler(value = {ForbiddenException.class})
    protected ResponseEntity<Object> handleForbidden(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }



    // 404 Not Found

    @ExceptionHandler(value = { EntityNotFoundException.class, ResourceNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, "The requested resource was not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


    // 405 Method Not Allowed

    // 406 Not Acceptable

    // 407 Proxy Authentication Required

    // 408 Request Timeout

    // 409 409 Conflict

    @ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class })
    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    //  410 Gone

    // 411 Length Required

    // 412 Precondition Failed
    @ExceptionHandler(value = {RequestHeaderException.class})
    protected ResponseEntity<Object> handleHeaderPrecondition(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request);
    }

    // 413 Request Entity Too Large

    // 414 Request-URI Too Long

    // 415 Unsupported Media Type

    // 416 Requested Range Not Satisfiable

    // 417 Expectation Failed

    //




    // 500

    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    /*500*/public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
