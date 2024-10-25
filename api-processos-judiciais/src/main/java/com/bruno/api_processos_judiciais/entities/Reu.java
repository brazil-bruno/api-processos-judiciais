package com.bruno.api_processos_judiciais.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "tb_reu")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Reu implements Serializable {
    private static final long serialVersionUIDLONG = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reuId;

    private String nomeReu;
}
