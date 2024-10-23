package desafiogenerations.models;

import desafiogenerations.payload.FuncionarioRequestPayload;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email_funcionario"})})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Metodo para controle de perfil, nesse caso o perfil é fixo
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email_funcionario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
