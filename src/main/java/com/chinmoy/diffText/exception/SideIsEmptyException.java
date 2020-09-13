package com.chinmoy.diffText.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception when SIDE is EMPTY.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "side cannt be Empty!")
public class SideIsEmptyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6866976977417190661L;

}
