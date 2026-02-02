package com.brasasplit.demo.domain;

import com.brasasplit.demo.domain.enums.TipoDespesa;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ItemDespesa extends BaseEntity {
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private TipoDespesa tipo;
}
