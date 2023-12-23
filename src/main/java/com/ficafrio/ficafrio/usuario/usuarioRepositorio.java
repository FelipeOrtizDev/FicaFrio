package com.ficafrio.ficafrio.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ficafrio.ficafrio.services.usuarioServices.usuarioResponseDTO;

@Repository
public interface usuarioRepositorio extends JpaRepository<usuarioEntity, Integer> {

    @Query("SELECT p FROM usuario p WHERE p.usuario = :usuario")
    Optional<usuarioEntity> findByUsuario(String usuario);

    @Query("SELECT p FROM usuario p WHERE p.usuario = :usuario")
    usuarioResponseDTO findByUsuarioId(String usuario);
}
