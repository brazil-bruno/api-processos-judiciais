package com.bruno.api_processos_judiciais.dtos;

import com.bruno.api_processos_judiciais.entities.Processo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProcessoDTO implements Serializable {
    private static final long serialVersionUIDLONG = 1L;

    private Long processoId;

    private BigInteger numeroProcesso;

    public ProcessoDTO(Processo entity) {
        processoId = entity.getProcessoId();
        numeroProcesso = entity.getNumeroProcesso();
    }
}
