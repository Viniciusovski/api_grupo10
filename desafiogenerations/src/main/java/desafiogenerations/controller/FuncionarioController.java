package desafiogenerations.controller;

import desafiogenerations.models.Funcionario;
import desafiogenerations.payload.FuncionarioCreateResponse;
import desafiogenerations.payload.FuncionarioRequestPayload;
import desafiogenerations.repository.FuncionarioRepository;
import desafiogenerations.service.FuncionarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/listar")
    public List<Funcionario> findAll(){

        return repository.findAll();

    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Funcionario> getAluno(@PathVariable Long id){
        Optional<Funcionario> aluno = this.repository.findById(id);

        return aluno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/criar")
    public ResponseEntity<FuncionarioCreateResponse> createFuncionario(@RequestBody FuncionarioRequestPayload payload){
        Funcionario newFuncionario = new Funcionario(payload);

        // Codifica a senha antes de salvar o funcionário
        String encodedPassword = passwordEncoder.encode(payload.senha());
        newFuncionario.setSenha(encodedPassword);

        try {
            this.repository.save(newFuncionario);
            return ResponseEntity.ok(new FuncionarioCreateResponse(newFuncionario.getId_funcionario()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Funcionario> editaFuncionario(@PathVariable Long id, @RequestBody FuncionarioRequestPayload payload){
        Optional<Funcionario> funcionario = this.repository.findById(id);

        if (funcionario.isPresent()){
            Funcionario editadoFuncionario = funcionario.get();

            editadoFuncionario.setNome_funcionario(payload.nome());
            editadoFuncionario.setEmail_funcionario(payload.email());
            editadoFuncionario.setSenha(payload.senha());
            editadoFuncionario.setCargo(payload.cargo());

            this.repository.save(editadoFuncionario);
            return ResponseEntity.ok(editadoFuncionario);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("deleta/{id}")
    public ResponseEntity<Funcionario> delete(@PathVariable Long id){
        Optional<Funcionario> funcionario = this.repository.findById(id);

        if (funcionario.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
