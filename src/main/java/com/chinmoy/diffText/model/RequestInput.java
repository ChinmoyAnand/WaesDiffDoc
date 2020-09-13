package com.chinmoy.diffText.model;


import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object that is received from APi Calls.
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestInput {
	
	/**
     * The base64 encoded Text
     */
	@NotBlank(message = "Message is mandatory")
    private String message;
    
    private DiffCriteria criteria;

}
