package com.brasasplit.demo.dto;

import lombok.Builder;

@Builder
public record ParticipanteResponseDTO(
    String id,
    String nome,
    String usuarioId,
    Boolean bebeAlcool,
    Boolean comeCarne) {
}
