package com.chinmoy.diffText.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * DTO for the Response Object
 */
@Getter
@Builder
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ResponseDTO {
	
	/**
     * The response type
     * <p>
     * {@see ResponseType}
     */
    private final DiffResult result;

    /**
     * The response message
     */
    private final List<String> offsets;

}
