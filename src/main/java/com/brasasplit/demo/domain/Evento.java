package com.brasasplit.demo.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "eventos")
@EqualsAndHashCode(callSuper = true)
public class Evento extends BaseEntity {
    @Id
    private String id;
    @NotBlank(message = "O nome do evento é obrigatório")
    private String nome;
    private LocalDate data;
    private String local;
    private String organizadorId;
    @Builder.Default
    private List<Participante> participantes = new ArrayList<>();
    @Builder.Default
    private List<Compra> compras = new ArrayList<>();
    @Version
    private Long version;
}
