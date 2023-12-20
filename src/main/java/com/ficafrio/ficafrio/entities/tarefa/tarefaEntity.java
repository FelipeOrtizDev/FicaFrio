package com.ficafrio.ficafrio.entities.tarefa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

import com.ficafrio.ficafrio.services.tarefaServices.tarefaRequestDTO;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "tarefa")
@Entity(name = "tarefa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idtarefa")
@ToString
public class tarefaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idtarefa;
    @Nonnull
    public String nometarefa;

    public String descricaotarefa;

    public LocalDate dataentrega;

    public String imagemperiodo;

    public tarefaEntity(tarefaRequestDTO data) {
        this.nometarefa = data.nometarefa();
        this.descricaotarefa = data.descricaotarefa();
        this.dataentrega = data.dataentrega();
        this.imagemperiodo = data.imagemperiodo();
    }

    public tarefaEntity(JSONObject data) {
        String dateString = data.getJSONObject("imagemperiodo").toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");

        this.nometarefa = data.getJSONObject("nometarefa").toString();
        this.descricaotarefa = data.getJSONObject("descricaotarefa").toString();
        this.dataentrega = LocalDate.parse(dateString, formatter);
        this.imagemperiodo = data.getJSONObject("imagemperiodo").toString();
    }
}
