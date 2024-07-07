package com.akilesh.socialmedia.common.exceptions;

/**
 * @author AkileshVasudevan
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
