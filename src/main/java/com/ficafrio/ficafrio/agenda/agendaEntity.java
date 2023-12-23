package com.ficafrio.ficafrio.agenda;

import java.util.HashSet;
import java.util.Set;

import com.ficafrio.ficafrio.services.agendaServices.agendaRequestDTO;
import com.ficafrio.ficafrio.tarefa.tarefaEntity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "agendas")
@Entity(name = "agendas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idagendas")
@ToString
public class agendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idagendas;
    @Nonnull
    public String nomeagenda;

    public String descricaoagenda;

    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    public Set<tarefaEntity> tarefas_fk = new HashSet<>();

    public String prioridade;

    public int quantidadetarefas;

    public int tarefasconcluidas;

    public agendaEntity(agendaRequestDTO data) {
        this.nomeagenda = data.nomeagenda();
        this.descricaoagenda = data.descricaoagenda();
        this.tarefas_fk = data.tarefas_fk();
        this.prioridade = data.prioridade();
        this.quantidadetarefas = data.quantidadetarefas();
        this.tarefasconcluidas = data.tarefasconcluidas();
    }
}
