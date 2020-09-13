package com.chinmoy.diffText.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
* Exception when the file content is not Base64.
*/

@ResponseStatus(value = HttpStatus.BAD_REQUEST,
       reason = "The content is invalid base64.")
public class InvalidFileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7074017838414003658L;

}
