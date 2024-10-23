package desafiogenerations.payload;

import desafiogenerations.models.Funcionario;
import jakarta.validation.constraints.Email;

import java.util.Optional;

public record FuncionarioDTO(Long id, String nome, @Email String email) {

}
