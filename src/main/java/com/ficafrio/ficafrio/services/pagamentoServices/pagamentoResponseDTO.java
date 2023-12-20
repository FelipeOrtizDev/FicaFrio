package com.ficafrio.ficafrio.services.pagamentoServices;

import java.time.LocalDate;

import com.ficafrio.ficafrio.entities.pagamento.pagamentoEntity;

public record pagamentoResponseDTO(Integer idpagamento, String nomecarta, String numerocarta, LocalDate validade) {

    public pagamentoResponseDTO(pagamentoEntity pagamento) {
        this(
            pagamento.getIdpagamento(),
            pagamento.getNomecartao(),
            pagamento.getNumerocartao(),
            pagamento.getValidade()
        );
    }
}
