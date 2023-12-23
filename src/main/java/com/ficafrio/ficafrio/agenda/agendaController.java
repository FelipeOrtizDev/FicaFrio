package com.ficafrio.ficafrio.agenda;

import java.util.Collections;
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

import com.ficafrio.ficafrio.services.GPT_API.chatGPT_API;
import com.ficafrio.ficafrio.services.agendaServices.agendaRequestDTO;
import com.ficafrio.ficafrio.services.agendaServices.agendaResponseDTO;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/agenda")

public class agendaController {

    @Autowired
    private agendaRepository agendaRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getall")
    public List<agendaResponseDTO> getAll() {
        try {
            List<agendaResponseDTO> agendaLista = agendaRepository.findAll().stream().map(agendaResponseDTO::new)
                    .toList();
            return agendaLista;
        } catch (Exception e) {
            System.out.println("Erro na solicitação. Código de resposta: " + e);
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/create")
    public void saveAgenda(@RequestBody @Validated agendaRequestDTO data) {

        agendaEntity agendaData = new agendaEntity(data);
        agendaRepository.save(agendaData);
    }

    @PostMapping("/organizaTarefa")
    public  String organizaTarefa(@RequestBody agendaRequestDTO data) throws Exception {
        return chatGPT_API.chatGPT(chatGPT_API.extrairNomesTarefasDeAgenda(data));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<agendaEntity> updateAgenda(@RequestBody @Validated agendaRequestDTO data) {
        Optional<agendaEntity> optionalAgenda = agendaRepository.findById(data.idagendas());

        if (optionalAgenda.isPresent()) {
            agendaEntity agendaUpdate = optionalAgenda.get();
            agendaUpdate.setIdagendas(data.idagendas());
            agendaUpdate.setNomeagenda(data.nomeagenda());
            agendaUpdate.setDescricaoagenda(data.descricaoagenda());
            ;
            agendaUpdate.setTarefas_fk(data.tarefas_fk());
            return ResponseEntity.ok(agendaUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/delete/{idagenda}")
    public void deletarAgenda(@PathVariable Integer idagenda) {
        agendaRepository.deleteById(idagenda);
    }
}
