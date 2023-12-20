package com.ficafrio.ficafrio.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ficafrio.ficafrio.entities.tarefa.tarefaEntity;
import com.ficafrio.ficafrio.services.tarefaServices.tarefaRequestDTO;
import com.ficafrio.ficafrio.services.tarefaServices.tarefaResponseDTO;
import com.ficafrio.ficafrio.entities.tarefa.tarefaRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/tarefa")

public class tarefaController {

    @Autowired
    private tarefaRepository tarefaRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getall")
    public List<tarefaResponseDTO> getAll() {

        List<tarefaResponseDTO> tarefaLista = tarefaRepository.findAll().stream().map(tarefaResponseDTO::new).toList();
        return tarefaLista;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/create")
    public void savetarefa(@RequestBody tarefaRequestDTO data) {
        tarefaEntity tarefaData = new tarefaEntity(data);
        tarefaRepository.save(tarefaData);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<tarefaEntity> updateProduct(@RequestBody @Validated tarefaRequestDTO data) {
        Optional<tarefaEntity> optionalProduct = tarefaRepository.findById(data.idtarefa());

        if (optionalProduct.isPresent()) {
            tarefaEntity tarefaUpdate = optionalProduct.get();
            tarefaUpdate.setIdtarefa(data.idtarefa());
            tarefaUpdate.setNometarefa(data.nometarefa());
            tarefaUpdate.setDescricaotarefa(data.descricaotarefa());
            tarefaUpdate.setDataentrega(data.dataentrega());
            tarefaUpdate.setImagemperiodo(data.imagemperiodo());
            return ResponseEntity.ok(tarefaUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/delete/{idtarefa}")
    public void deletartarefa(@PathVariable Integer idtarefa) {
        tarefaRepository.deleteById(idtarefa);
    }
}
