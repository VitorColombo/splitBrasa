package com.brasasplit.demo.controller;

import com.brasasplit.demo.dto.AuthenticationRequest;
import com.brasasplit.demo.dto.AuthenticationResponse;
import com.brasasplit.demo.dto.RegisterRequest;
import com.brasasplit.demo.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Login e registro de usuários")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(summary = "Cadastro de usuário", description = "Realiza o cadastro de usuário e retorna um token JWT como resposta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Erro de validação dos campos de entrada",
                    content = @Content(schema = @Schema(type = "object", example = "{\"email\": \"Formato inválido\", \"senha\": \"Senha fraca\"}")))
    })
    //o valid obriga que as regras do RegisterRequest sejam seguidas
    public ResponseEntity<AuthenticationResponse> registrar(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.registrar(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuário", description = "Realiza o login do usuário e retorna um token JWT como resposta")
    public ResponseEntity<AuthenticationResponse> logar(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.logar(request));
    }
}
