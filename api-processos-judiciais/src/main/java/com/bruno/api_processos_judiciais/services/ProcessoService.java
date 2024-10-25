package com.bruno.api_processos_judiciais.services;

import com.bruno.api_processos_judiciais.dtos.ProcessoDTO;
import com.bruno.api_processos_judiciais.dtos.ReuDTO;
import com.bruno.api_processos_judiciais.entities.Processo;
import com.bruno.api_processos_judiciais.entities.Reu;
import com.bruno.api_processos_judiciais.repositories.ProcessoRepository;
import com.bruno.api_processos_judiciais.repositories.ReuRepository;
import com.bruno.api_processos_judiciais.services.exceptions.DataBaseException;
import com.bruno.api_processos_judiciais.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcessoService {
    private final ProcessoRepository processoRepository;

    private final ReuRepository reuRepository;

    public ProcessoDTO salvarNovoProcesso(ProcessoDTO processoDTO) {
        try {
            Processo entity = Processo.builder()
                    .numeroProcesso(processoDTO.getNumeroProcesso())
                    .build();
            entity = processoRepository.save(entity);
            return new ProcessoDTO(entity);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("O número de processo já existe!");
        }
    }

    public Page<ProcessoDTO> buscarTodosNumerosProcessos(Pageable pageable) {
        Page<Processo> processos = processoRepository.findAll(pageable);
        return processos.map(ProcessoDTO::new);
    }

    @Transactional
    public void excluirProcessoPorNumero(BigInteger numeroProcesso) {
        Optional<Processo> processo = processoRepository.findBynumeroProcesso(numeroProcesso);
        Processo entity = processo.orElseThrow(() -> new ResourceNotFoundException("Número de processo não encontrado!"));
        processoRepository.deleteByNumeroProcesso(entity.getNumeroProcesso());
    }

    public ReuDTO adicionarReuAoProcesso(ReuDTO reuDTO, BigInteger numeroProcesso) {
        Optional<Processo> obj = processoRepository.findBynumeroProcesso(numeroProcesso);
        Processo processo = obj.orElseThrow(() -> new ResourceNotFoundException("Número de processo não encontrado!"));
        if (processo.getReu() == null) {
            Reu entity = Reu.builder()
                    .nomeReu(reuDTO.getNomeReu())
                    .build();
            entity = reuRepository.save(entity);
            processo.setReu(entity);
            processoRepository.save(processo);
            return new ReuDTO(entity);
        } else {
            throw new DataBaseException("Já existe um réu cadastrado no processo!");
        }
    }
}
