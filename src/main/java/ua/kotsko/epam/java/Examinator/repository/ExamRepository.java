package ua.kotsko.epam.java.Examinator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.kotsko.epam.java.Examinator.model.examEntity.Exam;

@Repository
public interface ExamRepository extends CrudRepository<Exam, Long>  {

}

