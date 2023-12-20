package com.ficafrio.ficafrio.entities.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepositorio extends JpaRepository<UsuarioModelo, Integer> {

    @Query("SELECT p FROM usuario p WHERE p.usuario = :usuario")
    Optional<UsuarioModelo> findByUsuario(String usuario);

        @Query("SELECT p FROM usuario p WHERE p.usuario = :usuario")
    UsuarioModelo findByUsuario1(String usuario);
}
