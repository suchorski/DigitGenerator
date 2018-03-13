package com.suchorski.digitgenerator.number.custom;

import com.suchorski.digitgenerator.exception.InvalidFormatException;
import com.suchorski.digitgenerator.number.generic.GenericNumber;

/**
 * Class for the CPF number
 * @author Thiago
 *
 */
public class CPFNumber extends GenericNumber {

	/**
	 * Constructor for the CPF number
	 * @param number number with digits
	 * @throws InvalidFormatException exception for invalid format
	 */
	public CPFNumber(String number) throws InvalidFormatException {
		super(number.substring(0, 9) + "-" + number.substring(9));
	}
	
	/**
	 * Constructor for the generic number
	 * @param genericNumber generic number
	 */
	public CPFNumber(GenericNumber genericNumber) {
		super(genericNumber);
	}
	
	/**
	 * Format a CPF number
	 * @return Return a formated CPF number
	 */
	@Override
	public String format() {
		return toString().replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
	}

}
