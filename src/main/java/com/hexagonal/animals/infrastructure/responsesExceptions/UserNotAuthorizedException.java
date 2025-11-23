package com.hexagonal.animals.infrastructure.responsesExceptions;

import com.hexagonal.animals.infrastructure.Utils.ErrorConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)

public class UserNotAuthorizedException extends APIException {
    public UserNotAuthorizedException() {
        super(3, ErrorConstants.USER_NOT_AUTHORIZED.getMessage());
    }
}
