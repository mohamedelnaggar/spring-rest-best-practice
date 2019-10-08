package com.silicon.payment.exception;

import com.silicon.payment.resource.ApiErrorResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(ExceptionHandlingController.class);

    @ExceptionHandler(SiliconResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResource> notFoundException(final SiliconResourceNotFoundException e) {
        log.error("Error: {} / {}", e.getErrorNumber(), e.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put(e.getResourceName().toString(), e.getMessage());
        ApiError apiError = new ApiError(e.getErrorNumber(), e.getMessage(), errors);
        return new ResponseEntity <>(new ApiErrorResource(apiError), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestParameterException.class)
    public ResponseEntity<ApiErrorResource> invalidRequestParameterException(final InvalidRequestParameterException e) {
        log.error("Error: {} / {}", e.getErrorNumber(), e.getMessage());
        ApiError apiError = new ApiError(e.getErrorNumber(), e.getMessage(), e.getErrorsMap());
        return new ResponseEntity <>(new ApiErrorResource(apiError), HttpStatus.BAD_REQUEST);
    }

}
