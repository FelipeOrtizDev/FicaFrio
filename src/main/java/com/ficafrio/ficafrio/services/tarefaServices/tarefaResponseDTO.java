package com.ficafrio.ficafrio.services.tarefaServices;

import java.time.LocalDate;

import com.ficafrio.ficafrio.entities.tarefa.tarefaEntity;

public record tarefaResponseDTO(Integer idtarefa, String nometarefa, String descricaotarefa, LocalDate dataentrega,
        String imagemperiodo) {

    public tarefaResponseDTO(tarefaEntity tarefa) {
        this(
                tarefa.getIdtarefa(),
                tarefa.getNometarefa(),
                tarefa.getDescricaotarefa(),
                tarefa.getDataentrega(),
                tarefa.getImagemperiodo());
    }
}
