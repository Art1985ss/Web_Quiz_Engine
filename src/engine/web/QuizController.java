package engine.web;

import engine.entity.*;
import engine.service.QuizService;
import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private final QuizService quizService;
    private final UserService userService;

    @Autowired
    public QuizController(QuizService quizService, UserService userService) {
        this.quizService = quizService;
        this.userService = userService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable long id) {
        Optional<Quiz> quiz = quizService.getQuizById(id);
        return ResponseEntity.of(quiz);
    }

    @GetMapping
    public ResponseEntity<Page<Quiz>> getAll(@RequestParam int page) {
        return ResponseEntity.ok(quizService.getAll(page));
    }

    @GetMapping("/completed")
    public ResponseEntity<Page<QuizCompleted>> findCompleted(@RequestParam(defaultValue = "0") Integer page,
                                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                                               Principal principal) {
        User user = userService.findByEmail(principal.getName());
        Page<QuizCompleted> completionsPage = quizService.findCompleted(user, page, pageSize);
        return ResponseEntity.ok(completionsPage);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Quiz> create(@Valid @RequestBody Quiz quiz, Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        quiz.setUser(user);
        return ResponseEntity.ok(quizService.create(quiz));
    }

    @PostMapping(path = "/{id}/solve")
    public ResponseEntity<QuizStatus> solve(@PathVariable long id,
                                            @RequestBody Answer answer,
                                            Principal principal) {
        User user = userService.findByEmail(principal.getName());
        return ResponseEntity.of(quizService.solve(answer, id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id, Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        quizService.delete(id, user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
