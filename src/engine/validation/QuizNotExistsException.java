package engine.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuizNotExistsException extends RuntimeException {
    public QuizNotExistsException(long id) {
        super("Quiz with id " + id + " doesn't exist");
        System.out.println(HttpStatus.NOT_FOUND);
    }
}
