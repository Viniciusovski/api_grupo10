package desafiogenerations.models;

import desafiogenerations.payload.FuncionarioRequestPayload;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email_funcionario"})})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_funcionario;

    @Column(nullable = false)
    private String nome_funcionario;

    @Email
    @Column(nullable = false)
    private String email_funcionario;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String cargo;

    public Funcionario(FuncionarioRequestPayload payload){
        this.nome_funcionario = payload.nome();
        this.email_funcionario = payload.email();
        this.senha = payload.senha();
        this.cargo = payload.cargo();

    }
}
