package com.suchorski.digitgenerator.generator.custom;

import com.suchorski.digitgenerator.exception.InvalidFormatException;
import com.suchorski.digitgenerator.exception.InvalidNumberException;
import com.suchorski.digitgenerator.generator.generic.GenericGenerator;
import com.suchorski.digitgenerator.number.custom.CPFNumber;
import com.suchorski.digitgenerator.number.generic.GenericNumber;

/**
 * Class for CPF generator
 * @author Thiago Suchorski
 *
 */
public class CPFGenerator extends GenericGenerator {

	private static final String[] FORMATS = { "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", "\\d{11}" };

	/**
	 * Generator for a CPF number
	 */
	public CPFGenerator() {
		super(2, 12, true);
	}

	/**
	 * Check for CPF number
	 * @param number Number to check
	 * @throws InvalidNumberException Exception for incorrect numbers
	 * @throws InvalidFormatException 
	 */
	@Override
	public void check(GenericNumber number) throws InvalidNumberException, InvalidFormatException {
		try {
			if (isFormated(FORMATS, number)) {
				super.check(number);				
			} else {
				throw new InvalidFormatException("Invalid CPF");
			}
		} catch (InvalidNumberException e) {
			throw new InvalidNumberException("Incorrect CPF");
		}
	}

	/**
	 * Randomizes a CPF number
	 * @return CPF number
	 */
	public CPFNumber randomize() {
		return new CPFNumber(randomize(9));
	}
	
}
