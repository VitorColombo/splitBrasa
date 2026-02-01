package com.brasasplit.demo.service;

import com.brasasplit.demo.domain.Evento;
import com.brasasplit.demo.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventoService {
    private final EventoRepository eventoRepository;
    public Evento criarEvento(Evento evento){
        return eventoRepository.save(evento);
    }
}
