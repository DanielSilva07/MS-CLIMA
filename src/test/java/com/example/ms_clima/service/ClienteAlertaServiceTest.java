package com.example.ms_clima.service;

import com.example.ms_clima.model.ClienteAlertaModel;
import com.example.ms_clima.repository.ClienteAlertaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteAlertaServiceTest {

    @Mock
    private ClienteAlertaRepository repository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private EmailClientService clienteAlertaService;

    @Test
    void deveSalvarClienteComSucesso() {
        ClienteAlertaModel cliente = new ClienteAlertaModel();
        cliente.setNome("Daniel Silva");
        cliente.setEmail("daniel@email.com");
        cliente.setRegiao("Rio de Janeiro");

        when(repository.save(any(ClienteAlertaModel.class))).thenReturn(cliente);

        ClienteAlertaModel resultado = clienteAlertaService.cadastrarInscrito(cliente);

        assertNotNull(resultado);
        verify(repository, times(1)).save(cliente);
        verify(emailService, times(1)).sendEmailAsync(cliente);
    }
}
