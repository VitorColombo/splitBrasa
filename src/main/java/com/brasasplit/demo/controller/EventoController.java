package com.brasasplit.demo.controller;

import com.brasasplit.demo.domain.Evento;
import com.brasasplit.demo.dto.EventoRequest;
import com.brasasplit.demo.dto.EventoResponse;
import com.brasasplit.demo.mapper.EventoMapper;
import com.brasasplit.demo.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private final EventoMapper mapper;

    @PostMapping
    @Operation(summary = "Criar Evento", description = "Cria um novo evento com os detalhes fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Erro de validação dos campos de entrada",
                    content = @Content(schema = @Schema(type = "object", example = "{\n" +
                            "  \"nome\": \"Nome inválido\",\n" +
                            "  \"data\": \"Formato de data inválida\",\n" +
                            "  \"local\": \"Local inválido\"\n" +
                            "}")))
    })
    public ResponseEntity<EventoResponse> criar(@RequestBody EventoRequest request){
        //dto de request->entidade
        Evento eventoParaSalvar = mapper.toEntity(request);
        // salvar entidade
        Evento eventoCriado = eventoService.criarEvento(eventoParaSalvar);
        // entidade->dto de resposta
        EventoResponse resposta = mapper.toResponse(eventoCriado);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(resposta);
    }
}
