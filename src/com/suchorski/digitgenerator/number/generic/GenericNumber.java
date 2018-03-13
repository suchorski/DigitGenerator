package com.suchorski.digitgenerator.number.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.suchorski.digitgenerator.exception.InvalidFormatException;

/**
 * Class for generic number generation
 * @author Thiago
 *
 */
public class GenericNumber {

	private List<Integer> number = new ArrayList<Integer>();
	private List<Integer> verifier = new ArrayList<Integer>();

	/**
	 * Constructor for the generic number
	 * @param number numbers
	 * @param verifier verifier digits
	 */
	public GenericNumber(List<Integer> number, List<Integer> verifier) {
		this.number = number;
		this.verifier = verifier;
	}

	/**
	 * Constructor for the generic number
	 * @param number numbers
	 * @param verifier verifier digits
	 * @throws InvalidFormatException exception for invalid format
	 */
	public GenericNumber(String number, String verifier) throws InvalidFormatException {
		this(number + "-" + verifier);
	}

	/**
	 * Constructor for the generic number
	 * @param number number with digits
	 * @throws InvalidFormatException exception for invalid format
	 */
	public GenericNumber(String number) throws InvalidFormatException {
		if (number.matches("\\d+-\\d+")) {
			String n = number.substring(0, number.indexOf("-"));
			String v = number.substring(number.indexOf("-") + 1);
			n.chars().forEach(c -> this.number.add(c - '0'));
			v.chars().forEach(c -> this.verifier.add(c - '0'));
		} else {
			throw new InvalidFormatException("Invalid number format");
		}
	}
	
	/**
	 * Constructor for the generic number
	 * @param genericNumber generic number
	 */
	public GenericNumber(GenericNumber genericNumber) {
		this(genericNumber.getNumber(), genericNumber.getVerifier());
	}

	/**
	 * Get the numbers
	 * @return numbers
	 */
	public List<Integer> getNumber() {
		return number;
	}

	/**
	 * Set the numbers
	 * @param number numbers
	 */
	public void setNumber(List<Integer> number) {
		this.number = number;
	}

	/**
	 * Get the verifier digits
	 * @return verifier digits
	 */
	public List<Integer> getVerifier() {
		return verifier;
	}

	/**
	 * Set the verifier digits
	 * @param verifier verifier digits
	 */
	public void setVerifier(List<Integer> verifier) {
		this.verifier = verifier;
	}

	/**
	 * Get the number as String
	 * @return number as String
	 */
	public String stringNumber() {
		return number.stream().map(i -> i.toString()).collect(Collectors.joining());
	}

	/**
	 * Get the verifier digits as String
	 * @return verifier digits as String
	 */
	public String stringVerifier() {
		return verifier.stream().map(i -> i.toString()).collect(Collectors.joining());
	}

	/**
	 * Return the number with verifier digits
	 */
	@Override
	public String toString() {
		return stringNumber() + stringVerifier();
	}

	/**
	 * Get the number with verifier digits formated
	 * @return the number formated
	 */
	public String format() {
		return stringNumber() + "-" + stringVerifier();
	}
	
	/**
	 * Check if is equals
	 */
	@Override
	public boolean equals(Object obj) {
	    final GenericNumber other = (GenericNumber) obj;
	    return (obj == null) || (!GenericNumber.class.isAssignableFrom(obj.getClass())) || (number.equals(other.getNumber()) && verifier.equals(other.getVerifier()));
	}

}
