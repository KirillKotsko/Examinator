package ua.kotsko.epam.java.Examinator.service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kotsko.epam.java.Examinator.model.examEntity.Exam;
import ua.kotsko.epam.java.Examinator.model.examEntity.Question;
import ua.kotsko.epam.java.Examinator.repository.ExamRepository;
import ua.kotsko.epam.java.Examinator.service.ExamService;

@Service
@Transactional(readOnly = true)
public class ExamServiceImpl implements ExamService {

	private ExamRepository examRepository;
	
	@Autowired
	public ExamServiceImpl(ExamRepository examRepository) {
		super();
		this.examRepository = examRepository;
	}

	@Override
	@Transactional
	public void saveExam(Exam exam) {
		examRepository.save(exam);
	}

	@Override
	public Exam getExam(Long id) {
		Optional<Exam> optionalExam = examRepository.findById(id);
		Exam exam = null;
        if (optionalExam.isPresent()) {
            exam = optionalExam.get();
            List<Question> distinctedQuestions = exam.getQuestions().stream().distinct().collect(Collectors.toList());
            exam.setQuestions(distinctedQuestions);
        } else {
            throw new RuntimeException("Exam not found for id : " + id);
        }
		return exam;
	}

	@Override
	@Transactional
	public void deleteExam(Long id) {
		examRepository.deleteById(id);
	}

	@Override
	public List<Exam> getExams() {
		List<Exam> exams = new ArrayList<>();
		examRepository.findAll().forEach(exams::add);
		return exams;
	}

}