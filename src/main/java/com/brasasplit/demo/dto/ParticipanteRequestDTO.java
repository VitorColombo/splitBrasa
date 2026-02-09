package com.brasasplit.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ParticipanteRequestDTO(
        @NotBlank(message = "O nome do participante é obrigatório.")
        String nome,
        String usuarioId,
        Boolean bebeAlcool,
        Boolean comeCarne
){}
