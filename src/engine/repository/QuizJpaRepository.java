package engine.repository;

import engine.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizJpaRepository extends JpaRepository<Quiz, Long> {
}
