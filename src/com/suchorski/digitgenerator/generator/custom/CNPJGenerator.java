package com.suchorski.digitgenerator.generator.custom;

import com.suchorski.digitgenerator.exception.InvalidFormatException;
import com.suchorski.digitgenerator.exception.InvalidNumberException;
import com.suchorski.digitgenerator.generator.generic.GenericGenerator;
import com.suchorski.digitgenerator.number.custom.CNPJNumber;
import com.suchorski.digitgenerator.number.generic.GenericNumber;

/**
 * Class for CNPJ generator
 * @author Thiago Suchorski
 *
 */
public class CNPJGenerator extends GenericGenerator {
	
	private static final String[] FORMATS = { "\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}-\\d{2}", "\\d{14}" };

	/**
	 * Generator for a CNPJ number
	 */
	public CNPJGenerator() {
		super(2, 9, true);
	}

	/**
	 * Check for CNPJ number
	 * @param number Number to check
	 * @throws InvalidFormatException Exception for invalid numbers
	 * @throws InvalidNumberException Exception for incorrect numbers
	 */
	@Override
	public void check(GenericNumber number) throws InvalidNumberException, InvalidFormatException {
		try {
			if (isFormated(FORMATS, number)) {
				super.check(number);				
			} else {
				throw new InvalidFormatException("Invalid CNPJ");
			}
		} catch (InvalidNumberException e) {
			throw new InvalidNumberException("Incorrect CNPJ");
		}
	}

	/**
	 * Randomizes a CNPJ number
	 * @return CNPJ number
	 */
	public CNPJNumber randomize() {
		return new CNPJNumber(randomize(12));
	}
	
}
