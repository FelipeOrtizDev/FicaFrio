package com.ficafrio.ficafrio.services.usuarioServices;

import java.util.Set;

import com.ficafrio.ficafrio.agenda.agendaEntity;
import com.ficafrio.ficafrio.pagamento.pagamentoEntity;
import com.ficafrio.ficafrio.usuario.usuarioEntity;

public record usuarioResponseDTO(int idusuario, String email, String senha, String usuario, String endereco,
        pagamentoEntity pagamento_fk, String telefone, Set<agendaEntity> agendas_fk, String datanascimento) {

    public usuarioResponseDTO(usuarioEntity data) {
        this(
                data.getIdusuario(),
                data.getEmail(),
                data.getSenha(),
                data.getUsuario(),
                data.getEndereco(),
                data.getPagamento_fk(),
                data.getTelefone(),
                data.getAgendas_fk(),
                data.getDatanascimento());
    }

}
