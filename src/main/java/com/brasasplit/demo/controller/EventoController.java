package com.brasasplit.demo.controller;

import com.brasasplit.demo.domain.Evento;
import com.brasasplit.demo.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/eventos")
@RequiredArgsConstructor
@Tag(name = "Evento", description = "Endpoints para gerenciar eventos")
public class EventoController {
    private final EventoService eventoService;
    @PostMapping
    @Operation(summary = "Criar Evento", description = "Cria um novo evento com os detalhes fornecidos")
    public ResponseEntity<Evento> criar(@RequestBody Evento evento){
        Evento eventoCriado = eventoService.criarEvento(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoCriado);
    }
}
