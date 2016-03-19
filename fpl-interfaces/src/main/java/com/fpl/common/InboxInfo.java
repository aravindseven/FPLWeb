package com.fpl.common;


public class InboxInfo {

	private String fromAddress;
	private String toAddress;
	private String bodyContent;
	/**
	 * @return the fromAddress
	 */
	public String getFromAddress() {
		return fromAddress;
	}
	/**
	 * @param fromAddress the fromAddress to set
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	/**
	 * @return the toAddress
	 */
	public String getToAddress() {
		return toAddress;
	}
	/**
	 * @param toAddress the toAddress to set
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	/**
	 * @return the bodyContent
	 */
	public String getBodyContent() {
		return bodyContent;
	}
	/**
	 * @param bodyContent the bodyContent to set
	 */
	public void setBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InboxInfo [fromAddress=" + fromAddress + ", toAddress="
				+ toAddress + ", bodyContent=" + bodyContent + "]";
	}
	
	
}
