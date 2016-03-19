package com.fpl.crypt;

public abstract class SimmetricCryptingFactory {

	private static SimmetricCryptingFactory factory;
	
	protected SimmetricCryptingFactory() {}
	
	public static SimmetricCryptingFactory getInstance() {
		if(factory == null) {
			synchronized (SimmetricCryptingFactory.class) {
				if(factory == null) {
					factory = new SimmetricCryptingFactoryImpl();
				}
			}
		}
		return factory;
	}
	
	public abstract SimmetricCrypting getDailyChangingSimmetricCrypting();
	
	public abstract SimmetricCrypting getSimmetricCrypting(String secretKey);
	
	public abstract SimmetricCrypting getSimmetricCrypting();
}
