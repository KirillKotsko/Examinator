package ua.kotsko.epam.java.Examinator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.kotsko.epam.java.Examinator.model.examEntity.*;
import ua.kotsko.epam.java.Examinator.service.ExamService;

@Controller
@RequestMapping("/exam")
public class ExamController {
	
	private ExamService service;
	
	@Autowired
	public ExamController(ExamService service) {
		this.service = service;
	}
	
	@GetMapping()
	public String index(Model model) {
		List<Exam> exams = service.getExams();
		model.addAttribute("exams", exams);
		return "index";
	}
	
	@GetMapping("/new")
	public String newExam(@ModelAttribute("exam") Exam exam) {
		exam.addQuestionAndAnswerPair();
		return "create_exam";
	}
	
	@GetMapping("/update/{id}")
	public String updateExam(@PathVariable("id") Long id, Model model) {
		Exam exam = service.getExam(id);
		System.out.println(exam);
        model.addAttribute("exam", exam);
        return "update_exam";
	}
	
	@GetMapping("/delete/{id}")
    public String deleteExam(@PathVariable (value = "id") long id) {
  
        service.deleteExam(id);
        return "redirect:/exam";
    }
	
	@PostMapping(params = "save")
	public String create(@ModelAttribute("exam") Exam exam) {
		exam.updateEntityOnQuestions();
		service.saveExam(exam);
		return "redirect:/exam";
	}
	
	@PostMapping(params = "addQuestion")
	public String addQuestion(@ModelAttribute("exam") Exam exam) {
		exam.addQuestionAndAnswerPair();
		if (exam.getId() != null)
			return "update_exam";
		else
			return "create_exam";
	}
	
	@PostMapping(params = "removeQuestion")
	public String removeQuestion(@ModelAttribute("exam") Exam exam, @RequestParam("removeQuestion") String index) {
		exam.getQuestions().remove(Integer.parseInt(index));
		if (exam.getId() != null)
			return "update_exam";
		else
			return "create_exam";
	}
	
	@PostMapping(params = "addAnswer")
	public String addAnswer(@ModelAttribute("exam") Exam exam, @RequestParam("addAnswer") String index) {
		exam.getQuestions().get(Integer.valueOf(index)).addAnswer(new Answer());
		if (exam.getId() != null)
			return "update_exam";
		else
			return "create_exam";
	}
	
	@PostMapping(params = "removeAnswer")
	public String removeAnswer(@ModelAttribute("exam") Exam exam, @RequestParam("removeAnswer") String values) {
		String[] indexs = values.split("_");
		exam.getQuestions().get(Integer.parseInt(indexs[0]))
			.getAnswers().remove(Integer.parseInt(indexs[1]));
		if (exam.getId() != null)
			return "update_exam";
		else
			return "create_exam";
	}
}
