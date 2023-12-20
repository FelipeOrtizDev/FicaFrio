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

import com.ficafrio.ficafrio.entities.pagamento.pagamentoEntity;
import com.ficafrio.ficafrio.entities.pagamento.pagamentoRepository;
import com.ficafrio.ficafrio.services.pagamentoServices.pagamentoRequestDTO;
import com.ficafrio.ficafrio.services.pagamentoServices.pagamentoResponseDTO;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/pagamento")

public class pagamentoController {

    @Autowired
    private pagamentoRepository pagamentoRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getall")
    public List<pagamentoResponseDTO> getAll() {

        List<pagamentoResponseDTO> agendaLista = pagamentoRepository.findAll().stream().map(pagamentoResponseDTO::new)
                .toList();
        return agendaLista;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/create")
    public void saveAgenda(@RequestBody pagamentoRequestDTO data) {
        pagamentoEntity pagamentoData = new pagamentoEntity(data);
        pagamentoRepository.save(pagamentoData);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<pagamentoEntity> updateProduct(@RequestBody @Validated pagamentoRequestDTO data) {
        Optional<pagamentoEntity> optionalProduct = pagamentoRepository.findById(data.idpagamento());

        if (optionalProduct.isPresent()) {
            pagamentoEntity pagamentoUpdate = optionalProduct.get();
            pagamentoUpdate.setIdpagamento(data.idpagamento());
            pagamentoUpdate.setNomecartao(data.nomecartao());
            pagamentoUpdate.setNumerocartao(data.numerocartao());
            pagamentoUpdate.setValidade(data.validade());
            return ResponseEntity.ok(pagamentoUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/delete/{idpagamento}")
    public void deletarAgenda(@PathVariable Integer idpagamento) {
        pagamentoRepository.deleteById(idpagamento);
    }
}
