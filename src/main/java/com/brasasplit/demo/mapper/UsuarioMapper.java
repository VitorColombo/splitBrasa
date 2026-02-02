package com.brasasplit.demo.mapper;

import com.brasasplit.demo.domain.Usuario;
import com.brasasplit.demo.dto.RegisterRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    // DTO de entrada -> entidade
    public Usuario toEntity(RegisterRequestDTO request) {
        return Usuario.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(request.senha())
                .build();
    }
}