package com.silicon.payment.exception;

public class SiliconResourceNotFoundException extends RuntimeException {

    private final String errorNumber = SiliconError.RESOURCE_NOT_FOUND.getErrorNumber();

    private final Resource resourceName;
    private final Long resourceUuid;

    public SiliconResourceNotFoundException(Resource resourceName, Long resourceId) {
        super(String.format("%s '%s' couldn't be found.", resourceName, resourceId));
        this.resourceName = resourceName;
        this.resourceUuid = resourceId;
    }

    public Resource getResourceName() {
        return resourceName;
    }

    public Long getResourceId() {
        return resourceUuid;
    }

    public String getErrorNumber() {
        return errorNumber;
    }

    public enum Resource {

        BOOK("Book"),
        DEPARTMENT("Department");

        private String value;

        Resource(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toLowerCase();
        }
    }
}
