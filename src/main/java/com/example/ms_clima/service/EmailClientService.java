package com.example.ms_clima.service;

import com.example.ms_clima.model.ClienteAlertaModel;
import com.example.ms_clima.repository.ClienteAlertaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailClientService {

    private final ClienteAlertaRepository repository;
    private final EmailService emailService;

    public EmailClientService(ClienteAlertaRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    @Transactional
    public ClienteAlertaModel cadastrarInscrito(ClienteAlertaModel inscrito) {
        inscrito.setDataInscricao(LocalDateTime.now());

        if (inscrito.getSubject() == null) {
            inscrito.setSubject("Alerta Climático - ODS 13");
        }

        ClienteAlertaModel novoInscrito = repository.save(inscrito);

        dispararEmailInscricao(novoInscrito);

        return novoInscrito;
    }

    public void dispararEmailInscricao(ClienteAlertaModel clienteModel) {
        try {
            emailService.sendEmailAsync(clienteModel);
        } catch (Exception e) {
            System.err.println("Erro ao acionar o envio de e-mail: " + e.getMessage());
        }
    }
}