package engine.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserNotAuthorException extends RuntimeException {
    public UserNotAuthorException(String userEmail) {
        super("User with email " + userEmail + " is not author of this quiz");
    }
}
