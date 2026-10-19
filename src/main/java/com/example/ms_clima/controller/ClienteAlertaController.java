package com.example.ms_clima.controller;
import com.example.ms_clima.model.ClienteAlertaModel;
import com.example.ms_clima.repository.ClienteAlertaRepository;
import com.example.ms_clima.service.EmailClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/alertas")
public class ClienteAlertaController {

    private final ClienteAlertaRepository repository;
    private final EmailClientService emailClientService;

    public ClienteAlertaController(ClienteAlertaRepository repository, EmailClientService emailClientService) {
        this.repository = repository;
        this.emailClientService = emailClientService;
    }

    @PostMapping
    public ResponseEntity<ClienteAlertaModel> cadastrarInscrito(@RequestBody ClienteAlertaModel inscrito) {
        ClienteAlertaModel novoInscrito = emailClientService.cadastrarInscrito(inscrito);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoInscrito);
    }

    @GetMapping
    public ResponseEntity<List<ClienteAlertaModel>> listarInscritos() {
        List<ClienteAlertaModel> lista = repository.findAll();
        return ResponseEntity.ok(lista);
    }
}