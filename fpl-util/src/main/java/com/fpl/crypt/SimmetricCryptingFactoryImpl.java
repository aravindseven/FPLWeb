package com.fpl.crypt;

import java.util.Arrays;

public class SimmetricCryptingFactoryImpl extends SimmetricCryptingFactory {

	private final SimmetricCrypting simmetricCrypting;
	private final SimmetricCrypting dailyChangingsimmetricCrypting;
	
	public SimmetricCryptingFactoryImpl() {
		simmetricCrypting = new MultiSimmetricCrypting(Arrays.asList(new SimmetricCrypting[] {
			new InternalSimmetricCrypting(DateKeyGenerator.TODAY)
		}));
			
		dailyChangingsimmetricCrypting = new MultiSimmetricCrypting(Arrays.asList(new SimmetricCrypting[] {
			new InternalSimmetricCrypting(DateKeyGenerator.TODAY),
			new InternalSimmetricCrypting(DateKeyGenerator.YESTERDAY),
		}));
	}
	
	@Override
	public SimmetricCrypting getDailyChangingSimmetricCrypting() {
		return dailyChangingsimmetricCrypting;
	}

	@Override
	public SimmetricCrypting getSimmetricCrypting(final String secretKey) {
		return new InternalSimmetricCrypting(new FixedKeyGenerator(secretKey));
	}

	@Override
	public SimmetricCrypting getSimmetricCrypting() {
		return simmetricCrypting;
	}
}
