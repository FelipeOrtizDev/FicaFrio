package com.ficafrio.ficafrio.usuario;

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

import com.ficafrio.ficafrio.services.usuarioServices.usuarioRequestDTO;
import com.ficafrio.ficafrio.services.usuarioServices.usuarioResponseDTO;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")

public class usuarioControle {

    @Autowired
    private usuarioRepositorio usuarioRepositorio;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/remover/{idusuario}")
    public void removerUsuario(@PathVariable int idusuario) {
        usuarioRepositorio.deleteById(idusuario);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Transactional
    @PutMapping("/alterar")
    public ResponseEntity<?> alterarUsuario(@RequestBody usuarioEntity um) {
        Optional<usuarioEntity> optionalProduct = usuarioRepositorio.findById(um.getIdusuario());
        if (optionalProduct.isPresent()) {
            usuarioEntity usuarioUpdate = optionalProduct.get();
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/cadastrar")
    public void saveUsuario(@RequestBody usuarioRequestDTO data) {
        usuarioEntity UsuarioData = new usuarioEntity(data);
        usuarioRepositorio.save(UsuarioData);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/listar")
    public Iterable<usuarioEntity> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody usuarioRequestDTO credenciais) {
        usuarioEntity usuarioEntityCredenciais = new usuarioEntity(credenciais);
        Optional<usuarioEntity> usuario = usuarioRepositorio.findByUsuario(usuarioEntityCredenciais.getUsuario());

        if (usuario.isEmpty() || !usuario.get().getSenha().equals(usuarioEntityCredenciais.getSenha())) {
            return ResponseEntity.badRequest().body("Usuário ou senha inválidos");
        }

        usuarioEntity resposta = new usuarioEntity();
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getuserinfo/{id}")
    public usuarioResponseDTO getUserById(@PathVariable usuarioEntity id) {
        return usuarioRepositorio.findByUsuarioId(id.getUsuario());
    }
}
