package com.brasasplit.demo.dto;

import com.brasasplit.demo.domain.Evento;

import java.time.LocalDate;

public record EventoResponse(
    String id,
    String nome,
    LocalDate data,
    String local,
    String organizadorId
) {
    //metodo estatico para converter Evento em EventoResponse
    public static EventoResponse fromEntity(Evento evento) {
        return new EventoResponse(
                evento.getId(),
                evento.getNome(),
                evento.getData(),
                evento.getLocal(),
                evento.getOrganizadorId()
        );
    }
}
