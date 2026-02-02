package com.brasasplit.demo.service;

import com.brasasplit.demo.domain.*;
import com.brasasplit.demo.dto.CompraRequestDTO;
import com.brasasplit.demo.dto.EventoRequestDTO;
import com.brasasplit.demo.exception.ResourceNotFoundException;
import com.brasasplit.demo.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoService {
    private final EventoRepository eventoRepository;

    public Evento criarEvento(Evento evento) {
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        evento.setOrganizadorId(usuarioLogado.getId());

        Participante donoParticipante = Participante.builder()
                .nome(usuarioLogado.getNome())
                .usuarioId(usuarioLogado.getId())
                .bebeAlcool(true)
                .comeCarne(true)
                .build();

        if (evento.getParticipantes() == null) {
            evento.setParticipantes(new ArrayList<>());
        }
        evento.getParticipantes().add(donoParticipante);

        return eventoRepository.save(evento);
    }

    public Evento adicionarCompra(String eventoId, Compra novaCompra) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com id: " + eventoId));

        Participante pagadorEncontrado = evento.getParticipantes().stream()
                .filter(p ->
                        p.getId().equals(novaCompra.getPagadorId()) ||
                        (p.getUsuarioId() != null && p.getUsuarioId().equals(novaCompra.getPagadorId()))
                )
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("O pagador informado não está na lista de participantes deste evento."));

        novaCompra.setPagadorId(pagadorEncontrado.getId());

        evento.getCompras().add(novaCompra);

        return eventoRepository.save(evento);
    }

    public Evento removerCompra (String eventoId, String compraId){
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com id: " + eventoId));

        Compra compraParaRemover = evento.getCompras().stream()
                .filter(c -> c.getId().equals(compraId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Compra não encontrada com id: " + compraId));

        evento.getCompras().remove(compraParaRemover);

        return eventoRepository.save(evento);
    }


}
