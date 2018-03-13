package com.suchorski.digitgenerator.generator.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.suchorski.digitgenerator.exception.InvalidFormatException;
import com.suchorski.digitgenerator.exception.InvalidNumberException;
import com.suchorski.digitgenerator.number.generic.GenericNumber;

/**
 * Class for generic generator
 * @author Thiago Suchorski
 *
 */
public class GenericGenerator {

	private static final String[] FORMATS = { "\\d+-\\d+", "\\d+" };

	protected int numVerifierDigits;
	protected int multiplierLimit;
	protected boolean timesTen;

	/**
	 * Generic constructor for generator
	 * @param numVerifierDigits Number of verifier digits
	 * @param multiplierLimit Limit of multiplier
	 * @param timesTen Multiplies the result by 10
	 */
	public GenericGenerator(int numVerifierDigits, int multiplierLimit, boolean timesTen) {
		this.numVerifierDigits = numVerifierDigits;
		this.multiplierLimit = multiplierLimit;
		this.timesTen = timesTen;
	}

	/**
	 * Get the number of verifier digits
	 * @return number of verifier digits
	 */
	public int getNumVerifierDigits() {
		return numVerifierDigits;
	}

	/**
	 * Set the number of verifier digits
	 * @param numVerifierDigits number of verifier digits
	 */
	public void setNumVerifierDigits(int numVerifierDigits) {
		this.numVerifierDigits = numVerifierDigits;
	}

	/**
	 * Get the number of multiplier limit
	 * @return number of multiplier limit
	 */
	public int getMultiplierLimit() {
		return multiplierLimit;
	}

	/**
	 * Set the number of multiplier limit
	 * @param multiplierLimit number of multiplier limit
	 */
	public void setMultiplierLimit(int multiplierLimit) {
		this.multiplierLimit = multiplierLimit;
	}

	/**
	 * Get if is multiplier ten
	 * @return if is multiplier ten
	 */
	public boolean isTimesTen() {
		return timesTen;
	}
	
	/**
	 * Set if is multiplier ten
	 * @param timesTen if is multiplier ten
	 */
	public void setTimesTen(boolean timesTen) {
		this.timesTen = timesTen;
	}

	/**
	 * Generate the verifier digits
	 * @param number Number to generate the verifier digit
	 * @return Return the verifier digit for the number
	 */
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
			verifier[i] = sum % 11 % 10;
		}
		try {
			return new GenericNumber(number, Arrays.toString(verifier).replaceAll("\\D", ""));
		} catch (InvalidFormatException e) {
			return null;
		}
	}

	/**
	 * Check for generic number with format XXX...X
	 * @param number Number to check
	 * @throws InvalidNumberException Exception that is thrown if the number is invalid 
	 * @throws InvalidFormatException 
	 */
	public void check(GenericNumber number) throws InvalidNumberException, InvalidFormatException {
		if (isFormated(FORMATS, number)) {
			if (!generate(number.stringNumber()).equals(number)) {
				throw new InvalidNumberException("Invalid number");
			}
		} else {
			throw new InvalidFormatException("Incorrect format number");
		}
	}

	/**
	 * Randomizes a generic valid number
	 * @param length Length of the number to be randomized without the verify digit
	 * @return Return a valid randomized generic number
	 */
	public GenericNumber randomize(int length) {
		List<Integer> random = new ArrayList<Integer>(Collections.nCopies(length, 0));
		return generate(random.stream().map(i -> ((Integer) (new Random().nextInt() % 10)).toString()).collect(Collectors.joining()));
	}

	/**
	 * Check if a number is formatted
	 * @param formats Formats for the number
	 * @param number Number to check
	 * @return Return if a number is well formatted
	 */
	public boolean isFormated(String[] formats, GenericNumber number) {
		return Arrays.stream(formats).anyMatch(f -> number.toString().matches(f));
	}

}