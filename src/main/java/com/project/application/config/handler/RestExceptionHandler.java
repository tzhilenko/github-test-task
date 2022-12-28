package com.project.application.config.handler;

import com.project.application.exception.AbstractGithubError;
import com.project.application.exception.ErrorAttributes;
import com.project.application.exception.UserNotFoundException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.NotAcceptableStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(UserNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorAttributes(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @Override
    protected Mono<ResponseEntity<Object>> handleNotAcceptableStatusException(NotAcceptableStatusException ex, HttpHeaders headers, HttpStatusCode status, ServerWebExchange exchange) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorAttributes(406, "Content type not supported, allowed content type: application/json")));

    }

    @ExceptionHandler(AbstractGithubError.class)
    protected ResponseEntity<Object> handleEntityNotFound(AbstractGithubError ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorAttributes(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }

}