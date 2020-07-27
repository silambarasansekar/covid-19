package com.covid19.springbootrestapi.location;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ResponseData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 128233631717874758L;
	private String isCrowded;

	public String getIsCrowded() {
		return isCrowded;
	}

	public void setIsCrowded(String isCrowded) {
		this.isCrowded = isCrowded;
	}
}
