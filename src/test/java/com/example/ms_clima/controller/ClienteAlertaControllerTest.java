package com.example.ms_clima.controller;

import com.example.ms_clima.model.ClienteAlertaModel;
import com.example.ms_clima.repository.ClienteAlertaRepository;
import com.example.ms_clima.service.EmailClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteAlertaController.class)
public class ClienteAlertaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmailClientService clienteAlertaService;

    @MockBean
    private ClienteAlertaRepository clienteAlertaRepository;

    @Test
    void deveCadastrarAlertaViaApiComSucesso() throws Exception {
        ClienteAlertaModel cliente = new ClienteAlertaModel();
        cliente.setNome("Maria Souza");
        cliente.setEmail("maria@email.com");
        cliente.setRegiao("São Paulo");

        when(clienteAlertaService.cadastrarInscrito(any(ClienteAlertaModel.class))).thenReturn(cliente);

        mockMvc.perform(post("/api/v1/alertas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().is2xxSuccessful());
    }
}
