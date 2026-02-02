package com.brasasplit.demo.controller;

import com.brasasplit.demo.domain.Usuario;
import com.brasasplit.demo.dto.AuthenticationRequestDTO;
import com.brasasplit.demo.dto.AuthenticationResponseDTO;
import com.brasasplit.demo.dto.RegisterRequestDTO;
import com.brasasplit.demo.mapper.UsuarioMapper;
import com.brasasplit.demo.service.AuthenticationService;
import com.brasasplit.demo.util.AuthConstants;
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
    private final UsuarioMapper mapper;

    @PostMapping("/register")
    @Operation(summary = "Cadastro de usuário", description = "Realiza o cadastro de usuário e retorna um token JWT como resposta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Email já cadastrado",
                    content = @Content(schema = @Schema(
                            type = "object",
                            example = AuthConstants.JSON_ERRO_EMAIL
                    ))),
            @ApiResponse(responseCode = "422", description = "Erro de validação dos campos de entrada",
                    content = @Content(schema = @Schema(type = "object", example = "{\"email\": \"Formato inválido\", \"senha\": \"Senha fraca\"}")))
    })
    //o valid obriga que as regras do RegisterRequest sejam seguidas
    public ResponseEntity<AuthenticationResponseDTO> registrar(@RequestBody @Valid RegisterRequestDTO request) {
        Usuario usuario = mapper.toEntity(request);

        return ResponseEntity.ok(authenticationService.registrar(usuario));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuário", description = "Realiza o login do usuário e retorna um token JWT como resposta")
    public ResponseEntity<AuthenticationResponseDTO> logar(@RequestBody AuthenticationRequestDTO request) {
        return ResponseEntity.ok(authenticationService.logar(request));
    }
}
