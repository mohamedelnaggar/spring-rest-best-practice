package com.silicon.library.exception;

public enum SiliconError {

    RESOURCE_NOT_FOUND("1001", ""),

    SILICON_INVALID_REQUEST_INPUTS("1002", "Invalid request inputs");

    private String errorNumber;
    private String errorMessage;

    SiliconError(String errorNumber, String errorMessage) {
        this.errorNumber = errorNumber;
        this.errorMessage = errorMessage;
    }

    public String getErrorNumber() {
        return errorNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
