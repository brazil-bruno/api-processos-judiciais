package com.bruno.api_processos_judiciais.resources.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
@Data
public class StandardError implements Serializable {
    private static final long serialVersionUIDLONG = 1L;

    private Instant timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
