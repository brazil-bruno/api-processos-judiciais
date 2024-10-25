package com.bruno.api_processos_judiciais.resources;

import com.bruno.api_processos_judiciais.dtos.ProcessoDTO;
import com.bruno.api_processos_judiciais.dtos.ReuDTO;
import com.bruno.api_processos_judiciais.services.ProcessoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/processos")
@RequiredArgsConstructor
public class ProcessoResource {
    private final ProcessoService processoService;

    @PostMapping("/salvar-novo-processo")
    @ResponseStatus(HttpStatus.CREATED)
    public ProcessoDTO salvarNovoProcesso(@RequestBody ProcessoDTO processoDTO) {
        return processoService.salvarNovoProcesso(processoDTO);
    }

    @GetMapping("/buscar-todos-processos")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProcessoDTO> buscarTodosProcessos(Pageable pageable) {
        return processoService.buscarTodosNumerosProcessos(pageable);
    }

    @DeleteMapping("excluir-processo-por-numero/{numeroProcesso}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirProcessoPorNumero(@PathVariable BigInteger numeroProcesso) {
        processoService.excluirProcessoPorNumero(numeroProcesso);
    }

    @PostMapping("/adicionar-reu-ao-processo/{numeroProcesso}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReuDTO adicionarReuAoProcesso(@RequestBody @Valid ReuDTO reuDTO, @PathVariable BigInteger numeroProcesso) {
        return processoService.adicionarReuAoProcesso(reuDTO, numeroProcesso);
    }
}
