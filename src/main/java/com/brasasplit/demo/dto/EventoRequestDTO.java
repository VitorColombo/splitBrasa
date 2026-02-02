package com.brasasplit.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EventoRequestDTO(
        @NotBlank(message = "O nome do evento é obrigatório")
        @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
        @Schema(example = "Aniversário do Marcão", description = "Nome curto e identificável do evento")
        String nome,

        @NotNull(message = "A data é obrigatória")
        @FutureOrPresent (message = "A data do evento não pode ser no passado")
        @Schema(example = "2028-02-20", description = "Data do evento deve ser hoje ou futura")
        LocalDate data,

        @NotBlank(message = "O local é obrigatório")
        @Size(max = 100, message = "O local deve ter no máximo 100 caracteres")
        @Schema(example = "Salão de Festas do Condomínio", description = "Endereço ou nome do local")
        String local
) {}