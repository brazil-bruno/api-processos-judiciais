package com.bruno.api_processos_judiciais.dtos;

import com.bruno.api_processos_judiciais.entities.Reu;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReuDTO implements Serializable {
    private static final long serialVersionUIDLONG = 1L;

    private Long reuId;

    @NotBlank(message = "Campo obrigatório!")
    private String nomeReu;

    public ReuDTO(Reu entity) {
        reuId = entity.getReuId();
        nomeReu = entity.getNomeReu();
    }
}
