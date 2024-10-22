package ch.wiss.wiss_quiz.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class handles Exceptions thrown by the application and returns well
 * formed JSON responses instead.
 * 
 * @author Patrick Meier
 *
 */
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return createDefaultErrorResponse(ex.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AnswerCouldNotBeSavedException.class)
	public ResponseEntity<Object> handleAnswerCouldNotBeSavedException(AnswerCouldNotBeSavedException ex,
			WebRequest request) {
		return createDefaultErrorResponse(ex.getMessage());
	}

	@ExceptionHandler(CategoryCouldNotBeSavedException.class)
	public ResponseEntity<Object> handleCategoryCouldNotBeSavedException(CategoryCouldNotBeSavedException ex,
			WebRequest request) {
		return createDefaultErrorResponse(ex.getMessage());
	}

	@ExceptionHandler(CategoryLoadException.class)
	public ResponseEntity<Object> handleCategoryLoadException(CategoryLoadException ex, WebRequest request) {
		return createDefaultErrorResponse(ex.getMessage());
	}

	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException ex, WebRequest request) {
		return createDefaultErrorResponse(ex.getMessage());
	}

	@ExceptionHandler(QuestionCouldNotBeSavedException.class)
	public ResponseEntity<Object> handleQuestionCouldNotBeSavedException(QuestionCouldNotBeSavedException ex,
			WebRequest request) {
		return createDefaultErrorResponse(ex.getMessage());
	}

	@ExceptionHandler(QuestionLoadException.class)
	public ResponseEntity<Object> handleQuestionLoadException(QuestionLoadException ex, WebRequest request) {
		return createDefaultErrorResponse(ex.getMessage());
	}

	private ResponseEntity<Object> createDefaultErrorResponse(String exceptionMessage) {
		Map<String, String> errors = new HashMap<>();
		errors.put("error", exceptionMessage);

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
