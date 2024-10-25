package com.bruno.api_processos_judiciais.tests;

import com.bruno.api_processos_judiciais.dtos.ProcessoDTO;
import com.bruno.api_processos_judiciais.dtos.ReuDTO;
import com.bruno.api_processos_judiciais.entities.Processo;
import com.bruno.api_processos_judiciais.entities.Reu;

import java.math.BigInteger;

public class Factory {

    public static Processo salvarNovoProcesso() {
        return Processo.builder()
                .processoId(1L)
                .numeroProcesso(BigInteger.valueOf(122345))
                .build();
    }

    public static ProcessoDTO salvarNovoProcessoDTO() {
        Processo processo = salvarNovoProcesso();
        return new ProcessoDTO(processo);
    }

    public static Reu salvarReu() {
        return Reu.builder()
                .reuId(1L)
                .nomeReu("João")
                .build();
    }

    public static ReuDTO salvarReuDTO() {
        Reu reu = salvarReu();
        return new ReuDTO(reu);
    }
}
