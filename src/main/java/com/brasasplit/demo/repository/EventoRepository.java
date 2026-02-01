package com.brasasplit.demo.repository;

import com.brasasplit.demo.domain.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

// Isso diz ao Spring: "Gerencie documentos 'Evento' onde o ID é 'String'"
public interface EventoRepository extends MongoRepository <Evento, String> {
    List<Evento> findByNomeContainingIgnoreCase(String nome);
}
