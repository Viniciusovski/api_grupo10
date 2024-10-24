package desafiogenerations.models;

import desafiogenerations.payload.FuncionarioRequestPayload;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="funcionarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_funcionario")
public class Funcionario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_funcionario;

    @Column(nullable = false)
    private String nome_funcionario;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String cargo;

    public Funcionario(FuncionarioRequestPayload payload){
        this.nome_funcionario = payload.nome();
        this.email = payload.email();
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
        return email;
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
