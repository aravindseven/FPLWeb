package com.fpl.crypt;

import java.util.Calendar;

import javax.crypto.SecretKey;

public class DateKeyGenerator extends AbstractKeyGenerator {

	private static final String DEFALUT_ENC_PREFIX = "ENC@";
	public static final KeyGenerator TODAY;
	public static final KeyGenerator YESTERDAY;
	public static final KeyGenerator TWO_DAYS_AGO;
	
	static {
		TODAY = new DateKeyGenerator();
		YESTERDAY = new DateKeyGenerator(-1);
		TWO_DAYS_AGO = new DateKeyGenerator(-2);
	}
	
	private DateKeyGenerator() { this(0); }
	
	protected DateKeyGenerator(final int dateInstance) {
		super(DEFALUT_ENC_PREFIX,DEFALUT_CIPHER_ALGORYTHM,getSecretKeyBuilder(dateInstance, DEFALUT_CIPHER_ALGORYTHM));
	}
	
	private static SecretKeyBuilder getSecretKeyBuilder(final int dateInstance, final String algorythm) {
		return new SecretKeyBuilder() {
			@Override
			public SecretKey getSecretKey() {
				final Calendar date = Calendar.getInstance();
				final Calendar today = Calendar.getInstance();
				date.clear();
				date.set(today.get(Calendar.DATE), today.get(Calendar.MONTH), today.get(Calendar.YEAR));
				date.add(Calendar.DATE, dateInstance);
				return createSecretKey(algorythm, date.getTimeInMillis());
			}
		};
	}
	
	public static SecretKeyBuilder getSecretKeyBuilder(final String algorythm, final String secretKey) {
		return new SecretKeyBuilder() {
			@Override
			public SecretKey getSecretKey() {
				return createSecretKey(algorythm, secretKey);
			}
		};
	}
}
