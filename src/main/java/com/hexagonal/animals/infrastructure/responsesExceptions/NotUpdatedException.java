package com.hexagonal.animals.infrastructure.responsesExceptions;

import com.hexagonal.animals.infrastructure.Utils.ErrorConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_MODIFIED)
public class NotUpdatedException extends APIException {
    public NotUpdatedException() {
        super(2, ErrorConstants.NOT_UPDATED.getMessage());
    }
}
