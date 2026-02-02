package com.brasasplit.demo.service;

import com.brasasplit.demo.domain.Usuario;
import com.brasasplit.demo.dto.AuthenticationRequestDTO;
import com.brasasplit.demo.dto.AuthenticationResponseDTO;
import com.brasasplit.demo.repository.UsuarioRepository;
import com.brasasplit.demo.util.AuthConstants;
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

    public AuthenticationResponseDTO registrar(Usuario usuario){
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException(AuthConstants.MSG_EMAIL_EM_USO);
        }

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        usuarioRepository.save(usuario);

        //permite o login direto apos o cadastro
        var jwtToken = jwtService.generateToken(usuario);
        return new AuthenticationResponseDTO(jwtToken);
    }

    public AuthenticationResponseDTO logar(AuthenticationRequestDTO request){
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
        return new AuthenticationResponseDTO(jwtToken);
    }
}
