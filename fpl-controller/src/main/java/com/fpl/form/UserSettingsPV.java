package com.fpl.form;

public class UserSettingsPV {

	private String primaryEmailId;
	private String alternateMailId;
	private String mobilephone;
	private String type;
	private String secretQuestionAnswer;
	
	/**
	 * @return the primaryEmailId
	 */
	public String getPrimaryEmailId() {
		return primaryEmailId;
	}
	
	/**
	 * @param primaryEmailId the primaryEmailId to set
	 */
	public void setPrimaryEmailId(final String primaryEmailId) {
		this.primaryEmailId = primaryEmailId;
	}
	
	/**
	 * @return the alternateMailId
	 */
	public String getAlternateMailId() {
		return alternateMailId;
	}
	
	/**
	 * @param alternateMailId the alternateMailId to set
	 */
	public void setAlternateMailId(final String alternateMailId) {
		this.alternateMailId = alternateMailId;
	}
	
	/**
	 * @return the mobilephone
	 */
	public String getMobilephone() {
		return mobilephone;
	}
	
	/**
	 * @param mobilephone the mobilephone to set
	 */
	public void setMobilephone(final String mobilephone) {
		this.mobilephone = mobilephone;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(final String type) {
		this.type = type;
	}
	
	/**
	 * @return the secretQuestionAnswer
	 */
	public String getSecretQuestionAnswer() {
		return secretQuestionAnswer;
	}
	
	/**
	 * @param secretQuestionAnswer the secretQuestionAnswer to set
	 */
	public void setSecretQuestionAnswer(final String secretQuestionAnswer) {
		this.secretQuestionAnswer = secretQuestionAnswer;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}
