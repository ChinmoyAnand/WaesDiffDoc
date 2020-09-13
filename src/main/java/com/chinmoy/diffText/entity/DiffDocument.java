package com.chinmoy.diffText.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * An entity object for Diff Document
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "doc")
public class DiffDocument {
	
	@Id
    private String id;
	
	@Field
    private String diffid;
    
	@Field
    private Binary doc;
	
	@Field
    private String side;

}
