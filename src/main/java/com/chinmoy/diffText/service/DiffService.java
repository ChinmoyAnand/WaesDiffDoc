package com.chinmoy.diffText.service;

import com.chinmoy.diffText.exception.DocNotExistException;
import com.chinmoy.diffText.exception.InvalidFileException;
import com.chinmoy.diffText.exception.SideIsEmptyException;
import com.chinmoy.diffText.model.DiffCriteria;
import com.chinmoy.diffText.model.ResponseDTO;
import com.chinmoy.diffText.model.Side;

/**
* The {@code DiffService} interface is the base where we can add a doc and 
* logic for checking the difference between two documents.
*/
public interface DiffService {
	
	/**
     * Get the diffin of both sides sides using the diffId
     *
     * @param diffId the diff identifier.
     * @return The response information.
     * @throws DocNotExistException         Doc is not found in DB.
     */
	public ResponseDTO getDiffById(String id, DiffCriteria criteria) throws DocNotExistException;
	
	/**
     * Adds the specified side for the diff document with the encoded Base64 content in the repository.
     *
     * @param diffId  the difference identifier.
     * @param message the base64 encoded message.
     * @param side    the Enum Side(LEFT/RIGHT).
     * @throws InvalidFileException When the based 64 contains an invalid.
     */
	public String addDoc(String diffId, String message, Side side) throws InvalidFileException, SideIsEmptyException;
    

}
