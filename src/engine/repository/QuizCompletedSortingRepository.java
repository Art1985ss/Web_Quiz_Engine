package engine.repository;

import engine.entity.QuizCompleted;
import engine.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCompletedSortingRepository extends PagingAndSortingRepository<QuizCompleted, Long> {
    Page<QuizCompleted> findByUser(User user, Pageable pageable);
}
