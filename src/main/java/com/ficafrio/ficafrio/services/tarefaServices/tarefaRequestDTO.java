package com.ficafrio.ficafrio.services.tarefaServices;

import java.time.LocalDate;

public record tarefaRequestDTO(Integer idtarefa, String nometarefa, String descricaotarefa, LocalDate dataentrega,
        String imagemperiodo) {
}
