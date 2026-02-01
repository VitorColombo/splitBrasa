package com.brasasplit.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participante {
    private String nome;
    private Boolean bebeAlcool;
    private Boolean comeCarne;
    private Boolean donoEvento;
}
