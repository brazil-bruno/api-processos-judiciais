package com.bruno.api_processos_judiciais.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "tb_processo")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Processo implements Serializable {
    private static final long serialVersionUIDLONG = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long processoId;

    @Column(unique = true)
    private BigInteger numeroProcesso;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "reu_id", referencedColumnName = "reuId")
    private Reu reu;
}
