package ch.wiss.wiss_quiz.exception;

/**
 * This Exception is thrown when a list of {@link Question} objects could not be
 * loaded.
 * 
 * @author Patrick Meier
 *
 */
public class QuestionLoadException extends RuntimeException {
	public QuestionLoadException() {
		super("Questions could not be loaded.");
	}

	public QuestionLoadException(int categoryId) {
		super("Questions for category with id '" + categoryId + "' could not be loaded");
	}
}
