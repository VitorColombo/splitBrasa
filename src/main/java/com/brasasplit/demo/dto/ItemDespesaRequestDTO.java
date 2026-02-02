package com.brasasplit.demo.dto;

import com.brasasplit.demo.domain.enums.TipoDespesa;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ItemDespesaRequestDTO(
        @NotBlank(message = "O nome não pode estar em branco")
        @Schema(example = "Costela")
        String nome,
        @NotNull(message = "O valor é obrigatório")
        @DecimalMin(value = "0.1", message = "O valor deve ser maior que zero")
        BigDecimal valor,
        @NotNull
        @Schema(example = "2")
        Integer quantidade,
        @NotNull(message = "O tipo de despesa é obrigatório")
        @Schema(example = "CARNE")
        TipoDespesa tipo
) {}
