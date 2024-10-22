package ch.wiss.wiss_quiz.exception;

/**
 * This Exception is thrown when there is a problem with saving a {@link Answer} object.
 * 
 * @author Patrick Meier
 *
 */
public class AnswerCouldNotBeSavedException extends RuntimeException {
	public AnswerCouldNotBeSavedException(String ansewerText) {
		super("The answer with text '" + ansewerText + "' could not be saved.");
	}
}