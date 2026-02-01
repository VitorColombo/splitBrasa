package com.brasasplit.demo.service;

import com.brasasplit.demo.domain.Evento;
import com.brasasplit.demo.domain.Usuario;
import com.brasasplit.demo.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventoService {
    private final EventoRepository eventoRepository;

    public Evento criarEvento(Evento evento){
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //regra de negocio: o organizador do evento é o usuario logado
        evento.setOrganizadorId(usuarioLogado.getId());

        return eventoRepository.save(evento);
    }
}
