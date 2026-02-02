package com.brasasplit.demo.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
@Builder
public record CompraResponseDTO(
        String id,
        String titulo,
        BigDecimal total,
        String pagadorId,
        String comprovanteUrl,
        List<ItemDespesaRequestDTO> itens
) {}