package com.silicon.library.resource;

import com.silicon.library.controller.ErrorController;
import com.silicon.library.exception.ApiError;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ApiErrorResource extends ResourceSupport {

    private final ApiError apiError;

    public ApiErrorResource(ApiError apiError) {
        this.apiError = apiError;
        String errorNumber = apiError.getErrorNumber();
        this.add(linkTo(methodOn(ErrorController.class, errorNumber).getError(errorNumber)).withSelfRel());
    }

    public ApiError getApiError() {
        return apiError;
    }
}
