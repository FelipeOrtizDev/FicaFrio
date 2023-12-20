package com.ficafrio.ficafrio.entities.usuario;

import java.util.HashSet;
import java.util.Set;

import com.ficafrio.ficafrio.entities.agenda.agendaEntity;
import com.ficafrio.ficafrio.entities.pagamento.pagamentoEntity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "usuario")
@Entity(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode( of = "idusuario")
@ToString
public class UsuarioModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idusuario;
    @Nonnull
    private String email;
    @Nonnull
    private String senha;
    @Nonnull
    private String usuario;
    private String endereco;
    @OneToOne
    private pagamentoEntity pagamento_fk;
    private String telefone;
    @OneToMany
    private Set<agendaEntity> agendas_fk = new HashSet<>();
    private String datanascimento;

    public UsuarioModelo(UsuarioModelo data) {
        this.email = data.getEmail();
        this.senha = data.getSenha();
        this.usuario = data.getUsuario();
        this.endereco = data.getEndereco();
        this.pagamento_fk = data.getPagamento_fk();
        this.telefone = data.getTelefone();
        this.agendas_fk = data.getAgendas_fk();
        this.datanascimento = data.getDatanascimento();
    }

    
}
