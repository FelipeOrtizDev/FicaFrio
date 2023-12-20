package com.ficafrio.ficafrio.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ficafrio.ficafrio.entities.usuario.UsuarioModelo;
import com.ficafrio.ficafrio.entities.usuario.UsuarioRepositorio;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")

public class UsuarioControle {

    @Autowired
    private UsuarioRepositorio ur;

    // Rota remover Usuario
    @DeleteMapping("/remover/{idusuario}")
    public void removerUsuario(@PathVariable int idusuario) {
        ur.deleteById(idusuario);
    }

    // Rota alterar Usuario
    @Transactional
    @PutMapping("/alterar")
    public ResponseEntity<?> alterarUsuario(@RequestBody UsuarioModelo um) {
        Optional<UsuarioModelo> optionalProduct = ur.findById(um.getIdusuario());
        if (optionalProduct.isPresent()) {
            UsuarioModelo usuarioUpdate = optionalProduct.get();
            usuarioUpdate.setIdusuario(um.getIdusuario());
            usuarioUpdate.setEmail(um.getEmail());
            usuarioUpdate.setSenha(um.getSenha());
            usuarioUpdate.setUsuario(um.getUsuario());
            usuarioUpdate.setEndereco(um.getEndereco());
            usuarioUpdate.setPagamento_fk(um.getPagamento_fk());
            usuarioUpdate.setTelefone(um.getTelefone());
            usuarioUpdate.setAgendas_fk(um.getAgendas_fk());
            usuarioUpdate.setDatanascimento(um.getDatanascimento());
            return ResponseEntity.ok(usuarioUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // rota cadastrar Usuario
    @PostMapping("/cadastrar")
    public void saveUsuario(@RequestBody UsuarioModelo data) {
        UsuarioModelo UsuarioData = new UsuarioModelo(data);
        ur.save(UsuarioData);
    }

    // rota listar Usuarios
    @GetMapping("/listar")
    public Iterable<UsuarioModelo> listarUsuarios() {
        return ur.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody UsuarioModelo credenciais) {
        Optional<UsuarioModelo> usuario = ur.findByUsuario(credenciais.getUsuario());

        if (usuario.isEmpty() || !usuario.get().getSenha().equals(credenciais.getSenha())) {
            return ResponseEntity.badRequest().body("Usuário ou senha inválidos");
        }

        UsuarioModelo resposta = new UsuarioModelo();
        resposta.setIdusuario(usuario.get().getIdusuario());
        resposta.setEmail(usuario.get().getEmail());
        resposta.setSenha(usuario.get().getSenha());
        resposta.setUsuario(usuario.get().getUsuario());
        resposta.setEndereco(usuario.get().getEndereco());
        resposta.setPagamento_fk(usuario.get().getPagamento_fk());
        resposta.setTelefone(usuario.get().getTelefone());
        resposta.setAgendas_fk(usuario.get().getAgendas_fk());
        resposta.setDatanascimento(usuario.get().getDatanascimento());
        
        System.out.println(resposta);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/getuserinfo/{id}")
    public UsuarioModelo getUserById(@PathVariable UsuarioModelo id) {
        UsuarioModelo usuario = ur.findByUsuario1(id.getUsuario());
        return usuario;
    }
}
