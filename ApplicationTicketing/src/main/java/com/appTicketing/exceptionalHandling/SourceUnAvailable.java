package com.appTicketing.exceptionalHandling;

public class SourceUnAvailable extends RuntimeException{
	
	String source;
	String field;
	Integer fieldValue;
	
	public SourceUnAvailable(String source, String field, Integer fieldValue) {
		super(String.format("%s not found with %s : %s", source, field, fieldValue));
		this.source = source;
		this.field = field;
		this.fieldValue = fieldValue;
	}
	
	

}
