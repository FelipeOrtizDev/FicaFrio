package com.ficafrio.ficafrio.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface pagamentoRepository extends JpaRepository<pagamentoEntity, Integer>{

}
