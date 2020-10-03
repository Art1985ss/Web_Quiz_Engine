package engine.repository;

import engine.entity.Quiz;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class QuizRepository {
    private final List<Quiz> quizList = new ArrayList<>();
    private static long id;


    public Quiz add(Quiz quiz) {
        quiz.setId(id);
        id++;
        quizList.add(quiz);
        return quiz;
    }

    public Optional<Quiz> getQuizById(long id) {
        return quizList.stream()
                .filter(quiz -> quiz.getId() == id)
                .findAny();
    }

    public List<Quiz> getAll() {
        return quizList;
    }
}
