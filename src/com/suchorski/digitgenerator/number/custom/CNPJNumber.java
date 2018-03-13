package com.suchorski.digitgenerator.number.custom;

import com.suchorski.digitgenerator.exception.InvalidFormatException;
import com.suchorski.digitgenerator.number.generic.GenericNumber;

/**
 * Class for the CNPJ number
 * @author Thiago
 *
 */
public class CNPJNumber extends GenericNumber {

	/**
	 * Constructor for the CNPJ number
	 * @param number number with digits
	 * @throws InvalidFormatException exception for invalid format
	 */
	public CNPJNumber(String number) throws InvalidFormatException {
		super(number.substring(0, 12) + "-" + number.substring(12));
	}
	
	/**
	 * Constructor for the generic number
	 * @param genericNumber generic number
	 */
	public CNPJNumber(GenericNumber genericNumber) {
		super(genericNumber);
	}
	
	/**
	 * Format a CPNJ number
	 * @return Return a formated CNPJ number
	 */
	@Override
	public String format() {
		return toString().replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
	}

}
