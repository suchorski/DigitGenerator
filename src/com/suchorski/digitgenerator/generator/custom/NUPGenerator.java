package com.suchorski.digitgenerator.generator.custom;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.suchorski.digitgenerator.exception.InvalidFormatException;
import com.suchorski.digitgenerator.exception.InvalidNumberException;
import com.suchorski.digitgenerator.generator.generic.GenericGenerator;
import com.suchorski.digitgenerator.number.custom.NUPNumber;
import com.suchorski.digitgenerator.number.generic.GenericNumber;

/**
 * Class for NUP generator
 * @author Thiago Suchorski
 *
 */
public class NUPGenerator extends GenericGenerator {

	private static final String[] FORMATS = { "\\d{5}\\.\\d{6}\\/\\d{4}-\\d{2}", "\\d{17}" };

	/**
	 * Generator for a NUP number
	 */
	public NUPGenerator() {
		super(2, 17, false);
	}

	/**
	 * Check for NUP number
	 * @param number Number to check
	 * @throws InvalidFormatException Exception for invalid numbers
	 * @throws InvalidNumberException Exception for incorrect numbers
	 */
	@Override
	public void check(GenericNumber number) throws InvalidNumberException, InvalidFormatException {
		if (isFormated(FORMATS, number)) {
			if (!generate(number.stringNumber()).equals(number)) {
				throw new InvalidNumberException("Incorrect NUP");
			}
		} else {
			throw new InvalidFormatException("Invalid NUP");
		}
	}

	/**
	 * Randomizes a NUP number
	 * @return NUP number
	 */
	public NUPNumber randomize() {
		String year = new SimpleDateFormat("yyyy").format(new Date());
		GenericNumber rand = randomize(9);
		return new NUPNumber(generate(rand.toString() + year));
	}

	/**
	 * Generate the verifier digits
	 * @param number Number to generate the verifier digit
	 * @return Return the verifier digit for the number
	 */
	@Override
	public GenericNumber generate(String number) {
		int[] numbers = new int[number.length()];
		int[] verifier = new int[numVerifierDigits];
		String reverseDigits = new StringBuilder(number).reverse().toString();
		for (int i = 0; i < reverseDigits.length(); ++i) {
			numbers[i] = Integer.parseInt("" + reverseDigits.charAt(i));
		}
		for (int i = 0; i < numVerifierDigits; ++i) {
			int multi = 2;			
			int sum = 0;
			for (int j = 0; j < i; ++j) {
				sum += verifier[j] * multi;
				if (++multi > multiplierLimit) {
					multi = 2;
				}
			}
			for (int j = 0; j < numbers.length; ++j) {
				sum += numbers[j] * multi;
				if (++multi > multiplierLimit) {
					multi = 2;
				}
			}
			if (timesTen) {
				sum *= 10;
			}
			verifier[i] = (11 - (sum % 11)) % 10;
		}
		try {
			return new GenericNumber(number, Arrays.toString(verifier).replaceAll("\\D", ""));
		} catch (InvalidFormatException e) {
			return null;
		}
	}

}
