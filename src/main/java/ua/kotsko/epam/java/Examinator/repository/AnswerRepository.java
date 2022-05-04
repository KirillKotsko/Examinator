package ua.kotsko.epam.java.Examinator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.kotsko.epam.java.Examinator.model.examEntity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>  {
	
}

