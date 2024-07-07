package com.akilesh.socialmedia.common.exceptions;

/**
 * @author AkileshVasudevan
 */
public class ForbiddenException extends RuntimeException {
    private final String responseContent;

    public ForbiddenException(String msg, String responseContent) {
        super(msg);
        this.responseContent = responseContent;
    }

    public String getResponseContent() {
        return this.responseContent;
    }
}
