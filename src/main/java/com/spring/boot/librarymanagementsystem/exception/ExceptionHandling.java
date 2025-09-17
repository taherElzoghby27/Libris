package com.spring.boot.librarymanagementsystem.exception;

import com.spring.boot.librarymanagementsystem.dto.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;
@ControllerAdvice
@Slf4j
public class ExceptionHandling {


    @ExceptionHandler(NotFoundResourceException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundResource(NotFoundResourceException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionDto(
                        HttpStatus.NOT_FOUND.value(),
                        exception.getMessage()
                )
        );
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionDto> handleNoResourceFound(NoResourceFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionDto(
                        HttpStatus.NOT_FOUND.value(),
                        exception.getMessage()
                )
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ExceptionDto> handleForbidden(AccessDeniedException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(
                        new ExceptionDto(
                                HttpStatus.FORBIDDEN.value(),
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler({
            BadCredentialsException.class,
            AccountExpiredException.class,
            InsufficientAuthenticationException.class,
            AuthenticationException.class,
            UsernameNotFoundException.class,
            CredentialsExpiredException.class,
            DisabledException.class,
            LockedException.class,
            AuthenticationCredentialsNotFoundException.class,
            InvalidCredentialsException.class,
            ExpiredTokenException.class,
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ExceptionDto> handleBadCredentialsException(Exception exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        new ExceptionDto(
                                HttpStatus.UNAUTHORIZED.value(),
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler({
            BadRequestException.class,
            IncorrectResultSizeDataAccessException.class
    })
    public ResponseEntity<ExceptionDto> handleBadRequestException(BadRequestException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionDto(
                        HttpStatus.BAD_REQUEST.value(),
                        exception.getMessage()
                )
        );
    }

    @ExceptionHandler({
            DataAccessException.class,
            TransactionException.class,
            TransactionSystemException.class,
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionDto> handleInternalServer(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ExceptionDto(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        exception.getMessage()
                )
        );
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ExceptionDto> handleDuplicateKeyException(DuplicateKeyException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ExceptionDto(
                        HttpStatus.CONFLICT.value(),
                        exception.getMessage()
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> handleValidationException(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionDto(
                        HttpStatus.BAD_REQUEST.value(),
                        exception.getMessage()
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGeneralException(Exception exception) {
        log.error("Unexpected error : ", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ExceptionDto(
                        500,
                        exception.getMessage()
                )
        );
    }
}
