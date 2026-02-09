package com.brasasplit.demo.service;

import com.brasasplit.demo.domain.*;
import com.brasasplit.demo.dto.CompraRequestDTO;
import com.brasasplit.demo.dto.EventoRequestDTO;
import com.brasasplit.demo.dto.ParticipanteRequestDTO;
import com.brasasplit.demo.exception.ResourceNotFoundException;
import com.brasasplit.demo.mapper.EventoMapper;
import com.brasasplit.demo.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class EventoService {
    private final EventoRepository eventoRepository;
    private final EventoMapper eventoMapper;

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

    public Evento atualizarEvento(String id, EventoRequestDTO dadosAtualizados) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));

        evento.setNome(dadosAtualizados.nome());
        evento.setData(dadosAtualizados.data());
        evento.setLocal(dadosAtualizados.local());

        return eventoRepository.save(evento);
    }

    public Evento adicionarCompra(String eventoId, Compra novaCompra) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com id: " + eventoId));

        String pagadorEncontrado = buscarPagadorNormalizado(evento, novaCompra.getPagadorId());

        novaCompra.setPagadorId(pagadorEncontrado);

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

    public Evento atualizarCompra(String eventoId, String compraId, CompraRequestDTO dto) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));

        Compra compra = evento.getCompras().stream()
                .filter(c -> c.getId().equals(compraId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Compra não encontrada"));

        compra.setTitulo(dto.titulo());
        compra.setComprovanteUrl(dto.comprovanteUrl());
        compra.setDataCompra(dto.dataCompra());

        compra.setItens(eventoMapper.toItemDespesaList(dto.itens()));

        if (!compra.getPagadorId().equals(dto.pagadorId())) {
            compra.setPagadorId(buscarPagadorNormalizado(evento, dto.pagadorId()));
        }

        return eventoRepository.save(evento);
    }

    public Evento adicionarParticipante(String eventoId, Participante novoParticipante) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com id: " + eventoId));

        if (novoParticipante.getUsuarioId() != null) {
            boolean jaExiste = evento.getParticipantes().stream()
                    .filter(p -> p.getUsuarioId() != null)
                    .anyMatch(p -> p.getUsuarioId().equals(novoParticipante.getUsuarioId()));

            if (jaExiste) {
                throw new IllegalArgumentException("Este usuário já está participando do evento.");
            }
        }

        evento.getParticipantes().add(novoParticipante);
        return eventoRepository.save(evento);
    }

    public Evento removerParticipante(String eventoId, String participanteId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));

        Participante participanteParaRemover = evento.getParticipantes().stream()
                .filter(p -> p.getId().equals(participanteId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Participante não encontrado"));

        if (participanteParaRemover.getUsuarioId() != null &&
                participanteParaRemover.getUsuarioId().equals(evento.getOrganizadorId())) {
            throw new IllegalArgumentException("O organizador do evento não pode ser removido da lista.");
        }

        boolean temComprasNoNome = evento.getCompras().stream()
                .anyMatch(compra -> compra.getPagadorId().equals(participanteId));

        if (temComprasNoNome) {
            throw new IllegalArgumentException("Não é possível remover este participante pois ele possui compras registradas. Remova ou edite as compras antes.");
        }

        evento.getParticipantes().remove(participanteParaRemover);

        return eventoRepository.save(evento);
    }

    public Evento atualizarParticipante(String eventoId, String participanteId, ParticipanteRequestDTO dados) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));

        Participante participante = evento.getParticipantes().stream()
                .filter(p -> p.getId().equals(participanteId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Participante não encontrado"));

        participante.setNome(dados.nome());
        participante.setBebeAlcool(dados.bebeAlcool());
        participante.setComeCarne(dados.comeCarne());

        return eventoRepository.save(evento);
    }

    private String buscarPagadorNormalizado(Evento evento, String idInformado) {
        return evento.getParticipantes().stream()
                .filter(p -> p.getId().equals(idInformado) ||
                        (p.getUsuarioId() != null && p.getUsuarioId().equals(idInformado)))
                .findFirst()
                .map(Participante::getId)
                .orElseThrow(() -> new IllegalArgumentException("Pagador não encontrado no evento."));
    }
}
