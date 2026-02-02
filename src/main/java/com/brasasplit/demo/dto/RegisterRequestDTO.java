package com.brasasplit.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
    @NotBlank(message = "O nome não pode estar em branco")
    @Schema(description = "Nome completo do usuário", example = "João do Churrasco")
    String nome,
    @NotBlank(message = "O email não pode estar em branco")
    @Email(message = "O email deve ser válido")
    @Schema(description = "Email do usuario", example = "email@teste.com")
    String email,
    @NotBlank(message = "Insira uma senha")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).+$",
            message = "A senha deve conter pelo menos 1 letra maiúscula, 1 número e 1 caractere especial"
    )
    @Schema(description = "Senha do usuário", example = "Senha@123")
    String senha
){}
