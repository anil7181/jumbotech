package com.tecart.pqp.common;

public class CommonKeyValueParameters {
	
	private String key;
	
	private String value;
	
	/**
	 * 
	 */
	public CommonKeyValueParameters() {
		super();
	}

	/**
	 * @param key
	 * @param value
	 */
	public CommonKeyValueParameters(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
