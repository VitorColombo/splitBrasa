package com.brasasplit.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record EventoRequest(
    @NotBlank(message = "O nome não pode estar em branco")
    @Schema(description = "Nome completo do usuário", example = "João do Churrasco")
    String nome,
    @NotBlank(message = "A data é um dado obrigatório")
    @FutureOrPresent(message = "A data não pode ser no passado")
    @Schema(description = "Data marcada para o evento", example = "24/01/2028")
    LocalDate data,
    @NotBlank(message = "Insira o nome do local")
    @Schema(description = "Local onde ocorrerá o evento", example = "Casa do rubinho")
    String local
){}
