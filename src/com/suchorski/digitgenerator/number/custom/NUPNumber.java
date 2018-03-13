package com.suchorski.digitgenerator.number.custom;

import com.suchorski.digitgenerator.exception.InvalidFormatException;
import com.suchorski.digitgenerator.number.generic.GenericNumber;

/**
 * Class for the NUP number
 * @author Thiago
 *
 */
public class NUPNumber extends GenericNumber {

	/**
	 * Constructor for the NUP number
	 * @param number number with digits
	 * @throws InvalidFormatException exception for invalid format
	 */
	public NUPNumber(String number) throws InvalidFormatException {
		super(number.substring(0, 15) + "-" + number.substring(15));
	}
	
	/**
	 * Constructor for the generic number
	 * @param genericNumber generic number
	 */
	public NUPNumber(GenericNumber genericNumber) {
		super(genericNumber);
	}
	
	/**
	 * Format a NUP number
	 * @return Return a formated NUP number
	 */
	@Override
	public String format() {
		return toString().replaceAll("(\\d{5})(\\d{6})(\\d{4})(\\d{2})", "$1.$2/$3-$4");
	}

}
