package com.fpl.crypt;



public class CryptTest {

	public static void main(final String[] args) throws Throwable {
		final String value = "ENC-aYCaVZXf/sl5+/mOuoGhx8iejFHykNIb";
		final String decodedValue = Encryption.decrypt(value, CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
		System.out.println(decodedValue);
	}

	/*public static void main(final String[] args) {
		final Map map = new HashMap();
		final NewRequestPV requestPV = new NewRequestPV();
		requestPV.setCountry("India");
		requestPV.setDescription("What type of car?\nsad\nAre you looking for life time insurance?\nasdasd");
		requestPV.setFollowUp("1");
		requestPV.setLocation("Chennai");
		requestPV.setPostalCode("600042");
		requestPV.setType("4");
		requestPV.setUrgency("1");
		final String newRequestJson = JsonUtil.toJsonString(requestPV);
		map.put("NewRequest", newRequestJson);
		final Collection<String> collection = new ArrayList<String>();
		final Domain domain = new Domain();
		domain.setId(1L);
		domain.setName("Car Loan");
		domain.setType("LOAN");
		final String domainJson = JsonUtil.toJsonString(domain);
		collection.add(domainJson);
		map.put("Domains", collection);
		System.out.println(JsonUtil.toJsonString(map));
	}*/
}
