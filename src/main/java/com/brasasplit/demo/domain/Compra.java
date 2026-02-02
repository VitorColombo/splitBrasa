package com.brasasplit.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Compra {
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String titulo;
    private String pagadorId;
    private String comprovanteUrl;
    @Builder.Default
    private LocalDateTime dataCompra = LocalDateTime.now();
    @Builder.Default
    private List<ItemDespesa> itens = new ArrayList<>();

    public BigDecimal getTotalCompra() {
        return itens.stream()
                .map(item -> item.getValor().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}