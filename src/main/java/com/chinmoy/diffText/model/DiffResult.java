package com.chinmoy.diffText.model;

/**
 * Enum to represent the Diffin result of both docs.
 */
public enum DiffResult {
	
	/**
     * Represents if both sides of the diff document are EQUAL.
     */
    EQUALS,

    /**
     * Represents if both sides of the diff document are NOT EQUAL IN SIZE.
     */
    NOT_EQUAL_SIZE,

    /**
     * Represents if both sides of the diff document are EQUAL bt content is different.
     */
    SAME_SIZE_BUT_DIFFNT_CONTENT

}
