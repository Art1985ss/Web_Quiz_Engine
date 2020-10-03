package engine.repository;

import engine.entity.Quiz;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizSortingRepository extends PagingAndSortingRepository<Quiz, Long> {
}
