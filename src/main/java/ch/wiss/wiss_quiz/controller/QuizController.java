package ch.wiss.wiss_quiz.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.wiss_quiz.exception.CategoryNotFoundException;
import ch.wiss.wiss_quiz.exception.QuestionLoadException;
import ch.wiss.wiss_quiz.model.Category;
import ch.wiss.wiss_quiz.model.CategoryRepository;
import ch.wiss.wiss_quiz.model.Question;
import ch.wiss.wiss_quiz.model.QuestionRepository;

@RestController
@RequestMapping(path = "/quiz")
public class QuizController {
	private static final int MAX_QUESTIONS = 3;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping(path = "")
	public ResponseEntity<List<Question>> getQuizQuestions(@RequestParam Integer cat_id) {

		Optional<Category> category = categoryRepository.findById(cat_id);
		if (category.isEmpty()) {
			throw new CategoryNotFoundException(cat_id);
		}

		List<Question> questions;
		try {
			questions = questionRepository.findByCategory(category.get());
		} catch (Exception ex) {
			throw new QuestionLoadException(cat_id);
		}

		if (questions.size() > MAX_QUESTIONS) {
			Collections.shuffle(questions);
			return ResponseEntity.ok(questions.subList(0, MAX_QUESTIONS));
		}

		return ResponseEntity.ok(questions);
	}
}