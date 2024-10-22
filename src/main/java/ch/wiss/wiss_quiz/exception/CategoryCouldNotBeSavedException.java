package ch.wiss.wiss_quiz.exception;

/**
 * This Exception is thrown when there is a problem with saving a
 * {@link Category} object.
 * 
 * @author Patrick Meier
 *
 */
public class CategoryCouldNotBeSavedException extends RuntimeException {
	public CategoryCouldNotBeSavedException(String categoryName) {
		super("The category with name '" + categoryName + "' could not be saved.");
	}
}