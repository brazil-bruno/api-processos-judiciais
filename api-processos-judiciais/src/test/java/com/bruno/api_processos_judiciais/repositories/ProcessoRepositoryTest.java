package com.bruno.api_processos_judiciais.repositories;

import com.bruno.api_processos_judiciais.entities.Processo;
import com.bruno.api_processos_judiciais.entities.Reu;
import com.bruno.api_processos_judiciais.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@DataJpaTest
public class ProcessoRepositoryTest {

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private ReuRepository reuRepository;

    @Test
    public void salvarDeveriaSalvarNovoProcessoQuandoProcessoIdENumeroProcessoNaoExistir() {
        Processo processo = Factory.salvarNovoProcesso();
        processo = processoRepository.save(processo);

        Optional<Processo> result = processoRepository.findBynumeroProcesso(processo.getNumeroProcesso());

        Assertions.assertNotNull(processo.getProcessoId());
        Assertions.assertNotNull(processo.getNumeroProcesso());
        Assertions.assertTrue(result.isPresent());
        Assertions.assertSame(result.get(), processo);
    }

    @Test
    public void salvarDeveriaSalvarNovoReuQuandoIdExistir() {
        Reu reu = Factory.salvarReu();
        reu = reuRepository.save(reu);

        Optional<Reu> result = reuRepository.findById(reu.getReuId());

        Assertions.assertNotNull(reu.getReuId());
        Assertions.assertTrue(result.isPresent());
        Assertions.assertSame(result.get(), reu);
    }

    @Test
    public void buscarTodosProcessosDeveriaRetornarPage() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<Processo> result = processoRepository.findAll(pageable);

        Assertions.assertNotNull(result);
    }

    @Test
    public void excluirDeveriaExcluirProcessoQuandoNumeroProcessoExistir() {

        Processo processo = Factory.salvarNovoProcesso();
        processo = processoRepository.save(processo);

        processoRepository.deleteByNumeroProcesso(processo.getNumeroProcesso());

        Optional<Processo> result = processoRepository.findBynumeroProcesso(processo.getNumeroProcesso());

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void adicionarDeveriaAdicionarReuAoProcessoQuandoNumeroProcessoExistir() {
        Processo processo = Factory.salvarNovoProcesso();
        Reu reu = Factory.salvarReu();
        reu = reuRepository.save(reu);
        processo.setReu(reu);
        processo = processoRepository.save(processo);

        Optional<Processo> result = processoRepository.findBynumeroProcesso(processo.getNumeroProcesso());

        Assertions.assertNotNull(processo.getReu());
        Assertions.assertTrue(result.isPresent());
    }
}
