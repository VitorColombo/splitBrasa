package com.brasasplit.demo.mapper;

import com.brasasplit.demo.domain.Evento;
import com.brasasplit.demo.dto.EventoRequest;
import com.brasasplit.demo.dto.EventoResponse;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {

    // DTO de entrada -> entidade
    public Evento toEntity(EventoRequest request) {
        return Evento.builder()
                .nome(request.nome())
                .data(request.data())
                .local(request.local())
                .build();
    }

    // entidade -> DTO de saida
    public EventoResponse toResponse(Evento entity) {
        return new EventoResponse(
                entity.getId(),
                entity.getNome(),
                entity.getData(),
                entity.getLocal(),
                entity.getOrganizadorId()
        );
    }
}