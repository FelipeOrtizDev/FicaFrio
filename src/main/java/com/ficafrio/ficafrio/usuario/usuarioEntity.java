package com.ficafrio.ficafrio.usuario;

import java.util.HashSet;
import java.util.Set;

import com.ficafrio.ficafrio.agenda.agendaEntity;
import com.ficafrio.ficafrio.pagamento.pagamentoEntity;
import com.ficafrio.ficafrio.services.usuarioServices.usuarioRequestDTO;

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
@EqualsAndHashCode(of = "idusuario")
@ToString
public class usuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario;
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

        public usuarioEntity(usuarioRequestDTO data) {
        this.email = data.email();
        this.senha = data.senha();
        this.usuario = data.usuario();
        this.endereco = data.endereco();
        this.pagamento_fk = data.pagamento_fk();
        this.telefone = data.telefone();
        this.agendas_fk = data.agendas_fk();
        this.datanascimento = data.datanascimento();
    }
}
