package com.chinmoy.diffText.exception.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ErrorDetails {
	
	private Date timestamp;
	private String message;
	private String details;
	

}
