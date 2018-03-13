package com.suchorski.digitgenerator.exception;

/**
 * Class for invalid format
 * @author Thiago
 *
 */
public class InvalidFormatException extends Exception {
	
	private static final long serialVersionUID = 282352034899139896L;

	/**
	 * Invalid format exception
	 * @param message invalid message
	 */
	public InvalidFormatException(String message) {
		super(message);
	}

}
