package ch.wiss.wiss_quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.wiss_quiz.exception.CategoryCouldNotBeSavedException;
import ch.wiss.wiss_quiz.exception.CategoryLoadException;
import ch.wiss.wiss_quiz.model.Category;
import ch.wiss.wiss_quiz.model.CategoryRepository;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/category") // This means URL's start with /demo (after Application path)
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;

	@PostMapping(path = "") // Map ONLY POST Requests
	public ResponseEntity<String> addNewCategory(@RequestParam String name) {

		Category c = new Category();
		c.setName(name);

		try {
			categoryRepository.save(c);
		} catch (Exception ex) {
			throw new CategoryCouldNotBeSavedException(name);
		}

		return ResponseEntity.ok("Saved");
	}

	@GetMapping(path = "")
	public ResponseEntity<Iterable<Category>> getAllCategories() {
		Iterable<Category> categories = null;

		try {
			categories = categoryRepository.findAll();
		} catch (Exception ex) {
			throw new CategoryLoadException();
		}

		return ResponseEntity.ok(categories);
	}
}