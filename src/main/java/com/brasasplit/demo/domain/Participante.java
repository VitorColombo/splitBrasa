package com.brasasplit.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Participante extends BaseEntity {
    private String nome;
    private Boolean bebeAlcool;
    private Boolean comeCarne;
    private Boolean donoEvento;
}
