package com.brasasplit.demo.repository;

import com.brasasplit.demo.domain.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository <Usuario, String> {
    //optional permite o retorno de um usuario ou de nenhum usuario para ser tratado pela controller
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
