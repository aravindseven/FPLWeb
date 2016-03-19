package com.fpl.util;

import java.math.BigDecimal;
import java.util.Random;

import org.apache.commons.lang.math.NumberUtils;

public final class NumberUtil {

	private NumberUtil() {}
	
	public static boolean isValidNumber(final String value) {
		return NumberUtils.isNumber(value);
	}
	
	public static Integer getRandomNumber() {
		final Random rand = new Random();
		return rand.nextInt((99999 - 10000) + 1) + 10000;
	}
	
	public static BigDecimal getConvertedNumber(final String value) {
		final BigDecimal bigDecimal = new BigDecimal(value);
		return bigDecimal;
	}
	
	public static String getFormattedNumber(final BigDecimal value) {
		return value.toPlainString();
	}
}
