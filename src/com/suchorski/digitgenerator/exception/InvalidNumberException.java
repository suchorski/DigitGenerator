package com.suchorski.digitgenerator.exception;

/**
 * Class for incorrect number
 * @author Thiago
 *
 */
public class InvalidNumberException extends Exception {
	
	private static final long serialVersionUID = -8665355662595983158L;

	/**
	 * Invalid number exception
	 * @param message invalid message
	 */
	public InvalidNumberException(String message) {
		super(message);
	}

}
