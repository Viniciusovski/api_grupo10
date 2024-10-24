package desafiogenerations.repository;

import desafiogenerations.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FuncionarioRepository extends JpaRepository <Funcionario, Long > {
//    @Query(
//            """
//            SELECT f FROM funcionarios f WHERE f.email_funcionario = :email
//            """
//    )
    UserDetails findByEmail(String email);

}
