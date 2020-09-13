package com.chinmoy.diffText.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chinmoy.diffText.entity.DiffDocument;
import com.chinmoy.diffText.model.Side;

@Repository
public interface DiffRepository extends MongoRepository<DiffDocument, String> {
	
	/**
     * Finds in database using the ID and SIDE TYPE of document.
     *
     * @param id the diff identifier
     * @param Side LEFT/RIGHT side
     * @return the document
     */
	Optional<DiffDocument> findByDiffidAndSide(String id, Side side); 
	}

