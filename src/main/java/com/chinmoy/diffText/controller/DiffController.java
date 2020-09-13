package com.chinmoy.diffText.controller;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chinmoy.diffText.exception.DocNotExistException;
import com.chinmoy.diffText.exception.InvalidFileException;
import com.chinmoy.diffText.exception.SideIsEmptyException;
import com.chinmoy.diffText.model.DiffCriteria;
import com.chinmoy.diffText.model.RequestInput;
import com.chinmoy.diffText.model.ResponseDTO;
import com.chinmoy.diffText.model.Side;
import com.chinmoy.diffText.service.DiffService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * Controller for handling the REST API for diffin doc
 * 
 */
@Api(tags = "diff", description = "Provides the endpoints to identify the differences of two base64 encoded JSON.")
@RestController
@RequestMapping("/diff/{id}")
@RequiredArgsConstructor
public class DiffController {
	
	private static final Logger LOG = LoggerFactory.getLogger(DiffController.class);
	
	@Autowired
	DiffService diffService;
	/**
     * Provides an endpoint to add the LEFT side of a diff document.
     *
     * @param diffId      the diff document identifier.
     * @param RequestInput the doc in String Base64
     * @return a representative object of the diff document.
     * @throws InvalidFileException       when the doc is empty or invalid.
     * @throws SideIsEmptyException when the side is not specified.
     */
    @PostMapping("/left")
    public ResponseEntity<String> addLeft(@PathVariable("id") String diffId,
    		@Valid @RequestBody RequestInput request) throws InvalidFileException, SideIsEmptyException{
    	LOG.info("Inside POST of RIGHT ID->{} Message-> {} ", diffId,request.getMessage());
        return ResponseEntity.ok(diffService.addDoc(diffId, request.getMessage(), Side.LEFT));
    }
    
    /**
     * Provides an endpoint to add the RIGHT side of a diff document.
     *
     * @param diffId      the diff document identifier.
     * @param RequestInput the doc in String Base64
     * @return a representative object of the diff document.
     * @throws InvalidFileException       when the doc is empty or invalid.
     * @throws SideIsEmptyException when the side is not specified.
     */
    @PostMapping("/right")
    public ResponseEntity<String> addRight(@PathVariable("id") String diffId,
    							@RequestBody RequestInput request) throws InvalidFileException, SideIsEmptyException{
    	LOG.info("Inside POST of RIGHT ID->{} Message-> {} ", diffId,request.getMessage());
        return ResponseEntity.ok(diffService.addDoc(diffId, request.getMessage(), Side.RIGHT));
    }
    
    /**
     * Provides an endpoint to verify the differences between the sides of a diff document.
     *
     * @param diffId the diff document identifier
     * @param Optional criteria  If its provided it will find the difference based on Position of Text content
     * @return an object that describes the differences between the sides of the diff document.
     * @throws DocNotExistException         when tdoc is not present in DB.
     */
    @GetMapping
    public ResponseEntity<ResponseDTO> getDiff(@PathVariable("id") String diffId,@RequestParam(required = false) String criteria) throws DocNotExistException{
    	 ResponseDTO response = criteria == null || criteria.equalsIgnoreCase("Default")?diffService.getDiffById(diffId,DiffCriteria.DEFAULT):
    		 diffService.getDiffById(diffId,DiffCriteria.TEXT);
    	return ResponseEntity.ok(response);

    }

}
