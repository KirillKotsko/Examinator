package ua.kotsko.epam.java.Examinator.service;

import java.util.List;

import ua.kotsko.epam.java.Examinator.model.examEntity.Exam;

public interface ExamService {
	
	public void saveExam(Exam exam);
	public Exam getExam(Long id);
	public void deleteExam(Long id);
	public List<Exam> getExams();
	
}