package com.bruno.api_processos_judiciais.repositories;

import com.bruno.api_processos_judiciais.entities.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

     void deleteByNumeroProcesso(BigInteger numeroProcesso);

    Optional<Processo> findBynumeroProcesso(BigInteger numeroProcesso);
}
