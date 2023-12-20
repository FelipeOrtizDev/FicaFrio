package com.ficafrio.ficafrio.services.pagamentoServices;

import java.time.LocalDate;

public record pagamentoRequestDTO(Integer idpagamento, String nomecartao, String numerocartao, LocalDate validade) {

}
