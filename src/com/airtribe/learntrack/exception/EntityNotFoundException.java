package com.airtribe.learntrack.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity, int id) {
        super(entity + " not found with ID: " + id);
    }
}