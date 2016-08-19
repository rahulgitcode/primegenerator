package com.primegenerator.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

public class FormData {
	@NotEmpty(message = "Please enter a value")
	@DecimalMin(value="2", message="Minimum value is 2")
	@DecimalMax(value="1000000", message="Maximum value is 1000000")
	@Pattern(regexp = "[0-9]*", message="Please enter valid integer number")
	public String inputNum;
	public String primeNumbers;
	
	public String getInputNum() {
		return inputNum;
	}
	public void setInputNum(String inputNum) {
		this.inputNum = inputNum;
	}
	public String getPrimeNumbers() {
		return primeNumbers;
	}
	public void setPrimeNumbers(String primeNumbers) {
		this.primeNumbers = primeNumbers;
	}
	
}
