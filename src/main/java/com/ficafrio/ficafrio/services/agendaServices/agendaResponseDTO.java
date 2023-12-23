package com.ficafrio.ficafrio.services.agendaServices;

import java.util.Set;

import com.ficafrio.ficafrio.agenda.agendaEntity;
import com.ficafrio.ficafrio.tarefa.tarefaEntity;

public record agendaResponseDTO(Integer idagendas, String nomeagenda, String descricaoagenda, Set<tarefaEntity> tarefas_fk,
        String prioridade, int quantidadetarefas, int tarefasconcluidas) {

    public agendaResponseDTO(agendaEntity agenda) {
        this(
                agenda.getIdagendas(),
                agenda.getNomeagenda(),
                agenda.getDescricaoagenda(),
                agenda.getTarefas_fk(),
                agenda.getPrioridade(),
                agenda.getQuantidadetarefas(),
                agenda.getTarefasconcluidas());
    }
}
