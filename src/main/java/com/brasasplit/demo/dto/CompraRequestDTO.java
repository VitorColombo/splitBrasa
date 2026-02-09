package com.brasasplit.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record CompraRequestDTO(
        @NotBlank(message = "O título da compra é obrigatório")
        @Schema(example = "Compras no Atacadão")
        String titulo,
        @NotBlank(message = "O pagador é obrigatório")
        @Schema(description = "ID do participante ou organizador que pagou")
        String pagadorId,
        @Schema(example = "https://s3.aws.com/minha-nota.jpg", description = "URL do comprovante da compra")
        String comprovanteUrl,
        @Schema(description = "Data da compra no formato AAAA-MM-DD, null = hoje", example = "2024-06-15")
        LocalDateTime dataCompra,
        @NotEmpty(message = "A compra deve ter pelo menos 1 item")
        @Valid
        List<ItemDespesaRequestDTO> itens
) {}