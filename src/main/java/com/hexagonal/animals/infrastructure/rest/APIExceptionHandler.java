package com.hexagonal.animals.infrastructure.rest;

import com.hexagonal.animals.infrastructure.responsesExceptions.APIException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.Optional;

@RestController
public class APIExceptionHandler extends AbstractErrorController {
    private static final String ERROR_PATH = "/error";
    private final ErrorAttributes errorAttributes;

    @Autowired
    public APIExceptionHandler(ErrorAttributes errorAttributes) {
        super(errorAttributes);
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping(path = ERROR_PATH)
    public ResponseEntity<?> handleError(HttpServletRequest request, WebRequest webRequest) {
        HttpStatus status = getStatus(request);
        ErrorAttributeOptions options = ErrorAttributeOptions.defaults();

        Map<String, Object> errors = getErrorAttributes(request, options);
        getApiException(webRequest).ifPresent(apiError -> {
            errors.put("message", apiError.message());
            errors.put("code", apiError.code());
        });
        // If you don't want to expose exception!
        errors.remove("exception");


        return ResponseEntity.status(status).body(errors);
    }

    private Optional<APIException> getApiException(WebRequest webRequest) {
        Throwable throwable = errorAttributes.getError(webRequest);
        if (throwable instanceof APIException exception) {
            return Optional.of(exception);
        }

        return Optional.empty();
    }
}
