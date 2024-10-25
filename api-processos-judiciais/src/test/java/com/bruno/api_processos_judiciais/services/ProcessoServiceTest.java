package com.bruno.api_processos_judiciais.services;

import static org.mockito.ArgumentMatchers.any;

import com.bruno.api_processos_judiciais.dtos.ProcessoDTO;
import com.bruno.api_processos_judiciais.entities.Processo;
import com.bruno.api_processos_judiciais.repositories.ProcessoRepository;
import com.bruno.api_processos_judiciais.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ProcessoServiceTest {

    @InjectMocks
    private ProcessoService processoService;
    @Mock
    private ProcessoRepository processoRepository;
    private PageImpl<Processo> page;

    @BeforeEach
    void setUp() throws Exception {

        Processo processo = Factory.salvarNovoProcesso();
        page = new PageImpl<>(List.of(processo));

        Mockito.when(processoRepository.save(any())).thenReturn(processo);
        Mockito.when(processoRepository.findAll((Pageable)any())).thenReturn(page);

    }

    @Test
    public void salvarDeveriaSalvarNovoProcessoQuandoIdNaoExistir() {
        Processo processo = Factory.salvarNovoProcesso();
        ProcessoDTO processoDTO = Factory.salvarNovoProcessoDTO();
        processoService.salvarNovoProcesso(processoDTO);

        Optional<Processo> result = processoRepository.findBynumeroProcesso(processo.getNumeroProcesso());

        Assertions.assertNotNull(processo.getProcessoId());
        Assertions.assertNotNull(result);

        Mockito.verify(processoRepository, Mockito.times(1)).findBynumeroProcesso(processo.getNumeroProcesso());
    }

    @Test
    public void buscarTodosProcessosDeveriaRetornarPage() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);

        Page<ProcessoDTO> result = processoService.buscarTodosNumerosProcessos(pageable);

        Assertions.assertNotNull(result);
    }
}
