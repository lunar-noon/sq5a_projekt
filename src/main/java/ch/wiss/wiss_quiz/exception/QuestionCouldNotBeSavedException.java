package ch.wiss.wiss_quiz.exception;

/**
 * This Exception is thrown when there is a problem with saving a
 * {@link Question} object.
 * 
 * @author Patrick Meier
 *
 */
public class QuestionCouldNotBeSavedException extends RuntimeException {
	public QuestionCouldNotBeSavedException(String questionText) {
		super("The question with text'" + questionText + "' could not be saved.");
	}
}
