package com.example.package_tracking.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public static EntityNotFoundException create(String message, Object field) {
        return new EntityNotFoundException(message + ": "+ field);
    }
}
