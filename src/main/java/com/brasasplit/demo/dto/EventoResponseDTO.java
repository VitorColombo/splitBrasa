package com.brasasplit.demo.dto;

import com.brasasplit.demo.domain.Evento;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record EventoResponseDTO(
        String id,
        String nome,
        LocalDate data,
        String local,
        String organizadorId,
        List<ParticipanteResponseDTO> participantes,
        List<CompraResponseDTO> compras
) {}
