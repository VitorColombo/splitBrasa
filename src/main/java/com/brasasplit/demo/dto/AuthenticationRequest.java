package com.brasasplit.demo.dto;

public record AuthenticationRequest(
        String email,
        String senha
) { }
