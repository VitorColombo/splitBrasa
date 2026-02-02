package com.brasasplit.demo.controller;

import com.brasasplit.demo.domain.Compra;
import com.brasasplit.demo.domain.Evento;
import com.brasasplit.demo.dto.CompraRequestDTO;
import com.brasasplit.demo.dto.CompraResponseDTO;
import com.brasasplit.demo.dto.EventoRequestDTO;
import com.brasasplit.demo.dto.EventoResponseDTO;
import com.brasasplit.demo.mapper.EventoMapper;
import com.brasasplit.demo.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<EventoResponseDTO> criar(@RequestBody EventoRequestDTO request){
        Evento eventoParaSalvar = mapper.toEntity(request);
        Evento eventoCriado = eventoService.criarEvento(eventoParaSalvar);
        EventoResponseDTO resposta = mapper.toResponse(eventoCriado);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(resposta);
    }

    @PostMapping("/{id}/compras")
    @Operation(summary = "Adicionar Compra", description = "Adiciona uma nova compra com itens ao evento")
    public ResponseEntity<EventoResponseDTO> adicionarCompra(@PathVariable String id, @RequestBody @Valid CompraRequestDTO request) {
        Compra compraDomain = mapper.toCompraDomain(request);

        Evento eventoAtualizado = eventoService.adicionarCompra(id, compraDomain);

        EventoResponseDTO response = mapper.toResponse(eventoAtualizado);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{eventoId}/compras/{compraId}")
    @Operation(summary= "Remover Compra", description = "Remove uma compra de um evento pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Remoção aceita, retorna o novo estado da entidade"),
            @ApiResponse(responseCode = "404", description = "Evento ou compra não encontrados pelo id",
                    content = @Content(schema = @Schema(type = "object", example = "{\n" +
                    "  \"erro\": \"Evento/Compra não encontrado com id: xxxxxxxx\",\n" +
                    "}")))
    })
    public ResponseEntity<EventoResponseDTO> removerCompra(@PathVariable String eventoId, @PathVariable String compraId){
        Evento eventoAtualizado = eventoService.removerCompra(eventoId, compraId);

        EventoResponseDTO response = mapper.toResponse(eventoAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
