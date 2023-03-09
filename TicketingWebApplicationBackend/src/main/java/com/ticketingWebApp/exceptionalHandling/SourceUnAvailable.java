package com.ticketingWebApp.exceptionalHandling;

public class SourceUnAvailable extends RuntimeException{
	
	String source;
	String field;
	Integer fieldValue;
	
	public SourceUnAvailable(String source, String field, Integer fieldValue) {
		super(String.format("%s un available in %s: %s", source, field, fieldValue));
		this.source = source;
		this.field = field;
		this.fieldValue = fieldValue;
	}

}
