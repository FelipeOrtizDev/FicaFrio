package com.ficafrio.ficafrio.services.usuarioServices;

import java.util.Set;

import com.ficafrio.ficafrio.agenda.agendaEntity;
import com.ficafrio.ficafrio.pagamento.pagamentoEntity;

public record usuarioRequestDTO(int idusuario, String email, String senha, String usuario, String endereco, pagamentoEntity pagamento_fk, String telefone, Set<agendaEntity> agendas_fk , String datanascimento) {

}
