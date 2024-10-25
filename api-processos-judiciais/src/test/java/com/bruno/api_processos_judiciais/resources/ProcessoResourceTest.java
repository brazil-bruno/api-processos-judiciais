package com.bruno.api_processos_judiciais.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bruno.api_processos_judiciais.dtos.ProcessoDTO;
import com.bruno.api_processos_judiciais.dtos.ReuDTO;
import com.bruno.api_processos_judiciais.services.ProcessoService;
import com.bruno.api_processos_judiciais.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigInteger;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ProcessoResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProcessoService processoService;

    @Autowired
    private ObjectMapper objectMapper;

    private BigInteger existingNumeroProcesso;
    private ProcessoDTO processoDTO;

    private ReuDTO reuDTO;
    private PageImpl<ProcessoDTO> page;

    @BeforeEach
    void setUp() throws Exception {

        existingNumeroProcesso = BigInteger.valueOf(122345);

        processoDTO = Factory.salvarNovoProcessoDTO();
        reuDTO = Factory.salvarReuDTO();
        page = new PageImpl<>(List.of(processoDTO));

        when(processoService.salvarNovoProcesso(any())).thenReturn(processoDTO);
        when(processoService.buscarTodosNumerosProcessos(any())).thenReturn(page);
        doNothing().when(processoService).excluirProcessoPorNumero(existingNumeroProcesso);
        when(processoService.adicionarReuAoProcesso(any(), any())).thenReturn(reuDTO);

    }

    @Test
    public void salvarDeveriaRetornarProcessoDTOCreated() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(processoDTO);
        ResultActions result =
                mockMvc.perform(post("/api/processos/salvar-novo-processo")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.processoId").exists());
        result.andExpect(jsonPath("$.numeroProcesso").exists());
    }

    @Test
    public void buscarTodosProcessosDeveriaRetornarPage() throws Exception {
        ResultActions result =
                mockMvc.perform(get("/api/processos/buscar-todos-processos")
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    public void excluirDeveriaRetornarNoContentQuandoNumeroProcessoExistir() throws Exception {
        ResultActions result =
                mockMvc.perform(delete("/api/processos/excluir-processo-por-numero/{numeroProcesso}", existingNumeroProcesso)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNoContent());
    }

    @Test
    public void adicionarDeveriaRetornarReuDTOCreated() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(reuDTO);
        ResultActions result =
                mockMvc.perform(post("/api/processos/adicionar-reu-ao-processo/{numeroProcesso}", existingNumeroProcesso)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.reuId").exists());
        result.andExpect(jsonPath("$.nomeReu").exists());
    }
}
