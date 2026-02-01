package com.brasasplit.demo.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "eventos")
public class Evento {
    @Id
    private String id;
    @NotBlank(message = "O nome do evento é obrigatório")
    private String nome;
    private LocalDate data;
    private List<Participante> participantes = new ArrayList<>();
    private List<ItemDespesa> despesas = new ArrayList<>();
}
