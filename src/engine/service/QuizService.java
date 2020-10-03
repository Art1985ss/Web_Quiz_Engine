package engine.service;

import engine.entity.*;
import engine.repository.QuizCompletedSortingRepository;
import engine.repository.QuizSortingRepository;
import engine.validation.QuizNotExistsException;
import engine.validation.UserNotAuthorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizSortingRepository quizRepository;
    private final QuizCompletedSortingRepository quizCompletionsRepository;

    @Autowired
    public QuizService(QuizSortingRepository quizRepository, QuizCompletedSortingRepository quizCompletionsRepository) {
        this.quizRepository = quizRepository;
        this.quizCompletionsRepository = quizCompletionsRepository;
    }

    public Quiz create(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Optional<Quiz> getQuizById(long id) {
        return quizRepository.findById(id);
    }

    public Page<Quiz> getAll(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return quizRepository.findAll(pageable);
    }

    public Page<QuizCompleted> findCompleted(User user, int page, int elements) {
        Pageable pageable = PageRequest.of(page, elements, Sort.by("completedAt").descending());
        return quizCompletionsRepository.findByUser(user, pageable);
    }

    public Optional<QuizStatus> solve(Answer answer, long quizId, User user) {
        Optional<Quiz> quizOptional = getQuizById(quizId);
        if (quizOptional.isEmpty()) {
            return Optional.empty();
        }
        Quiz quiz = quizOptional.get();
        List<Integer> correctAnswer = new ArrayList<>(quiz.getAnswer());
        if (answer == null) {
            answer = new Answer();
        }
        List<Integer> userAnswer = answer.getAnswer();
        Collections.sort(correctAnswer);
        Collections.sort(userAnswer);
        if (correctAnswer.equals(userAnswer)) {
            QuizCompleted quizCompleted = new QuizCompleted();
            quizCompleted.setQuizId(quiz.getId());
            quizCompleted.setUser(user);
            quizCompleted.setCompletedAt(LocalDateTime.now());
            quizCompletionsRepository.save(quizCompleted);
            return Optional.of(new QuizStatus(true));
        }
        return Optional.of(new QuizStatus(false));
    }

    public void delete(long id, User user) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotExistsException(id));
        User temp = quiz.getUser();
        if (!temp.equals(user)) {
            throw new UserNotAuthorException(user.getEmail());
        }
        quizRepository.deleteById(id);
    }
}
