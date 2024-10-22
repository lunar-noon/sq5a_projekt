package ch.wiss.wiss_quiz.exception;

/**
 * This Exception is thrown when a list of {@link Category} objects could not be
 * loaded.
 * 
 * @author Patrick Meier
 *
 */
public class CategoryLoadException extends RuntimeException {
	public CategoryLoadException() {
		super("Categories could not be loaded.");
	}
}
