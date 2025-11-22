package com.hexagonal.animals.infrastructure.responsesExceptions;

import com.hexagonal.animals.infrastructure.Utils.ErrorConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends APIException {
    public NotFoundException() {
        super(1, ErrorConstants.ANIMAL_NOT_FOUND.getMessage());
    }
}
