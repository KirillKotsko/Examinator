package ua.kotsko.epam.java.Examinator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ua.kotsko.epam.java.Examinator.model.examEntity.Exam;

@Controller
public class TestController {
	
	@GetMapping("/")
	public String showHome() {
		return "index";
	}
}
