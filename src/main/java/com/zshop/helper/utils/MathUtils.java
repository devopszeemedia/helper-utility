package com.zshop.helper.utils;

public class MathUtils {

	public static double addDoubles(double... val) {
		double sum = 0;
		for (double d : val) {
			sum = Double.sum(sum, d);
		}
		return sum;
	}

	public static double multiply(int decimalPlace, double... val) {
		double multiply = 1;
		double roundingOffVal = Math.pow(10.0, decimalPlace);
		for (double d : val) {
			multiply = multiply * d;
		}
		return Math.round(multiply * roundingOffVal) / roundingOffVal;
	}

	public static double roundOf(int decimalPlace, double val1) {
		double roundingOffVal = Math.pow(10.0, decimalPlace);
		return Math.round(val1 * roundingOffVal) / roundingOffVal;
	}

	public static double calPerc(double percVal, double totalVal) {
		return (percVal * totalVal) / 100;
	}
}
