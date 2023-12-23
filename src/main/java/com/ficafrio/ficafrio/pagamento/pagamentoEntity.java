package com.ficafrio.ficafrio.pagamento;

import java.time.LocalDate;

import com.ficafrio.ficafrio.services.pagamentoServices.pagamentoRequestDTO;

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

@Table(name = "pagamento")
@Entity(name = "pagamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idpagamento")

public class pagamentoEntity {

    @Id
    @Nonnull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idpagamento;
    @Nonnull
    public String nomecartao;
    @Nonnull
    public String numerocartao;
    @Nonnull
    public LocalDate validade;

    public pagamentoEntity(pagamentoRequestDTO data) {
        this.idpagamento = data.idpagamento();
        this.nomecartao = data.numerocartao();
        this.numerocartao = data.numerocartao();
        this.validade = data.validade();
    }
}
