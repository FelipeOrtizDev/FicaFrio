package com.ficafrio.ficafrio.services.agendaServices;

import java.util.Set;

import com.ficafrio.ficafrio.entities.tarefa.tarefaEntity;

public record agendaRequestDTO(Integer idagendas, String nomeagenda, String descricaoagenda, Set<tarefaEntity> tarefas_fk,
        String prioridade, int quantidadetarefas, int tarefasconcluidas) {

}
