package com.brasasplit.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDespesa {
    private String descricao;
    private String pagoPor;
    private BigDecimal valor;
}
