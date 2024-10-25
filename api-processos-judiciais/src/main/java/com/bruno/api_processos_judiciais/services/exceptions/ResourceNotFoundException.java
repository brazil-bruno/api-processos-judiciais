package com.bruno.api_processos_judiciais.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUIDLONG = 1L;

    public ResourceNotFoundException(String msg) {
        super(msg);
    }

}
