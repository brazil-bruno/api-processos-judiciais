package com.bruno.api_processos_judiciais.services.exceptions;

public class DataBaseException extends RuntimeException {
    private static final long serialVersionUIDLONG = 1L;

    public DataBaseException(String msg) {
        super(msg);
    }
}
