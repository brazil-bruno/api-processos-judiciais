package com.bruno.api_processos_judiciais.repositories;

import com.bruno.api_processos_judiciais.entities.Reu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReuRepository extends JpaRepository<Reu, Long> {
}
