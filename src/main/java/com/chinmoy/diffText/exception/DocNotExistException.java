package com.chinmoy.diffText.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception when Doc is not present in DB for the iD
 * 
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The given doc does not exist")
public class DocNotExistException extends NoSuchElementException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
