package com.brasasplit.demo.mapper;

import com.brasasplit.demo.domain.Compra;
import com.brasasplit.demo.domain.Evento;
import com.brasasplit.demo.domain.ItemDespesa;
import com.brasasplit.demo.domain.Participante;
import com.brasasplit.demo.dto.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventoMapper {

    // DTO de entrada -> Entidade
    public Evento toEntity(EventoRequestDTO request) {
        return Evento.builder()
                .nome(request.nome())
                .data(request.data())
                .local(request.local())
                .build();
    }

    // Entidade -> DTO de saida
    public EventoResponseDTO toResponse(Evento entity) {
        return EventoResponseDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .data(entity.getData())
                .local(entity.getLocal())
                .organizadorId(entity.getOrganizadorId())

                .compras(entity.getCompras().stream()
                        .map(this::toCompraResponse)
                        .toList())

                .participantes(entity.getParticipantes().stream()
                        .map(this::toParticipanteResponse)
                        .toList())
                .build();
    }

    // DTO Compra -> Entidade Compra
    public Compra toCompraDomain(CompraRequestDTO dto) {
        return Compra.builder()
                .titulo(dto.titulo())
                .pagadorId(dto.pagadorId())
                .comprovanteUrl(dto.comprovanteUrl())
                .itens(toItemDespesaList(dto.itens()))
                .build();
    }

    // Auxiliar Listas
    private List<ItemDespesa> toItemDespesaList(List<ItemDespesaRequestDTO> dtos) {
        return dtos.stream()
                .map(this::toItemDespesaDomain)
                .toList();
    }

    // Auxiliar Item DTO -> Entidade
    private ItemDespesa toItemDespesaDomain(ItemDespesaRequestDTO dto) {
        return ItemDespesa.builder()
                .nome(dto.nome())
                .valor(dto.valor())
                .quantidade(dto.quantidade())
                .tipo(dto.tipo())
                .build();
    }

    // Entidade Compra -> DTO Response
    public CompraResponseDTO toCompraResponse(Compra compra) {
        return CompraResponseDTO.builder()
                .id(compra.getId())
                .titulo(compra.getTitulo())
                .total(compra.getTotalCompra())
                .pagadorId(compra.getPagadorId())
                .comprovanteUrl(compra.getComprovanteUrl())
                .itens(compra.getItens().stream()
                        .map(item -> ItemDespesaRequestDTO.builder() // Builder aqui também!
                                .nome(item.getNome())
                                .valor(item.getValor())
                                .quantidade(item.getQuantidade())
                                .tipo(item.getTipo())
                                .build())
                        .toList())
                .build();
    }

    // Entidade Participante -> DTO Response
    public  ParticipanteResponseDTO toParticipanteResponse(Participante participante){
        return ParticipanteResponseDTO.builder()
                .id(participante.getId())
                .usuarioId(participante.getUsuarioId())
                .nome(participante.getNome())
                .bebeAlcool(participante.getBebeAlcool())
                .comeCarne(participante.getComeCarne())
                .build();
    }
}