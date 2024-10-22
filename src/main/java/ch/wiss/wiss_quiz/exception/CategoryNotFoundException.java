package ch.wiss.wiss_quiz.exception;

/**
 * This Exception is thrown when a specific {@link Category} object could not be
 * loaded.
 * 
 * @author Patrick Meier
 *
 */
public class CategoryNotFoundException extends RuntimeException {
	public CategoryNotFoundException(int categoryId) {
		super("The category with id '" + categoryId + "' could not be found.");
	}
}
