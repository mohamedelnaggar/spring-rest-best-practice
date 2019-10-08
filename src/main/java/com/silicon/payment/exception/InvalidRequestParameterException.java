package com.silicon.payment.exception;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvalidRequestParameterException extends RuntimeException {

    private Errors rawErrors;

    public InvalidRequestParameterException(Errors errors) {
        super("Invalid request inputs ...");
        this.rawErrors = errors;
    }

    public String getErrorNumber() {
        return SiliconError.SILICON_INVALID_REQUEST_INPUTS.getErrorNumber();
    }

    public Map<String, String> getErrorsMap() {
        Map<String, String> errorsMap = new HashMap<>();
        List<FieldError> allErrors = this.rawErrors.getFieldErrors();

        for (FieldError err : allErrors) {
            errorsMap.put(err.getField(), err.getDefaultMessage());
        }

        return errorsMap;
    }
}
