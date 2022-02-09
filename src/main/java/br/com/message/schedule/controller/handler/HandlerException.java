package br.com.message.schedule.controller.handler;

import br.com.message.schedule.domain.model.handler.ApiException;
import br.com.message.schedule.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;
    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<ApiException> handleScheduleNotFoundException(
            ScheduleNotFoundException ex) {
        return ResponseEntity.status(NOT_FOUND)
                .body(createResponse(ex.getMessage(), NOT_FOUND.value()));
    }

    @ExceptionHandler(ScheduleDateInvalidException.class)
    public ResponseEntity<ApiException> handleScheduleDateInvalidException(
            ScheduleDateInvalidException ex) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(createResponse(ex.getMessage(), BAD_REQUEST.value()));
    }

    @ExceptionHandler(TypeNotFoundException.class)
    public ResponseEntity<ApiException> handleTypeNotFoundException(TypeNotFoundException ex) {
        return ResponseEntity.status(NOT_FOUND)
                .body(createResponse(ex.getMessage(), NOT_FOUND.value()));
    }

    @ExceptionHandler(RecipientInvalidException.class)
    public ResponseEntity<ApiException> handleInvalidArgumentException(RecipientInvalidException ex) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(createResponse(ex.getMessage(), BAD_REQUEST.value()));
    }

    @ExceptionHandler(RecipientNotFoundException.class)
    public ResponseEntity<ApiException> handleRecipientNotFoundException(
            RecipientNotFoundException ex) {
        return ResponseEntity.status(NOT_FOUND)
                .body(createResponse(ex.getMessage(), NOT_FOUND.value()));
    }

    private ApiException createResponse(String message, int status) {
        return ApiException.newBuilder().message(message).status(status).build();
    }
}
