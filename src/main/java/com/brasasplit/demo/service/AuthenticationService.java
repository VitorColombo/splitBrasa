package com.brasasplit.demo.service;

import com.brasasplit.demo.domain.Usuario;
import com.brasasplit.demo.dto.AuthenticationRequest;
import com.brasasplit.demo.dto.AuthenticationResponse;
import com.brasasplit.demo.dto.RegisterRequest;
import com.brasasplit.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse registrar(RegisterRequest request){
        Usuario usuario  = Usuario.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(passwordEncoder.encode(request.senha()))
                .build();

        usuarioRepository.save(usuario);

        //permite o login direto apos o cadastro
        var jwtToken = jwtService.generateToken(usuario);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse logar(AuthenticationRequest request){
        //o spring security faz a validacao do usuario e senha
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.senha()
                )
        );

        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(usuario);
        return new AuthenticationResponse(jwtToken);
    }
}
