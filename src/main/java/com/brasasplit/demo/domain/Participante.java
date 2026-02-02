package com.brasasplit.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder; // Adicione o Builder, ajuda muito
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Participante extends BaseEntity {
    @Builder.Default
    private String id = java.util.UUID.randomUUID().toString();
    private String nome;
    private String usuarioId;
    @Builder.Default
    private Boolean bebeAlcool = true;
    @Builder.Default
    private Boolean comeCarne = true;
}