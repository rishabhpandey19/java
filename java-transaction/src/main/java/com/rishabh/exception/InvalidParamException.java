package com.rishabh.exception;


/**
 * The Class InvalidParamException.
 */
public class InvalidParamException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3088897936189782394L;
	
	/**
	 * Instantiates a new invalid param exception.
	 *
	 * @param message the message
	 */
	public InvalidParamException(String message) {
		super(message);
	}

}
