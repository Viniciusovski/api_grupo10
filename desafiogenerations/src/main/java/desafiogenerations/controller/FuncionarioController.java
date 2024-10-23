package desafiogenerations.controller;

import desafiogenerations.models.Funcionario;
import desafiogenerations.payload.FuncionarioCreateResponse;
import desafiogenerations.payload.FuncionarioDTO;
import desafiogenerations.payload.FuncionarioRequestPayload;
import desafiogenerations.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/listar")
    public ResponseEntity<List<FuncionarioDTO>> findAll(){

        List<FuncionarioDTO> funcionarios = repository.findAll()
                .stream()
                .map(funcionario -> new FuncionarioDTO(
                        funcionario.getId_funcionario(),
                        funcionario.getNome_funcionario(),
                        funcionario.getEmail()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(funcionarios);

    }

    @GetMapping("listar/{id}")
    public ResponseEntity<FuncionarioDTO> getFuncionario(@PathVariable Long id) {
        Optional<Funcionario> funcionario = this.repository.findById(id);

        return funcionario.map(f -> ResponseEntity.ok(
                        new FuncionarioDTO(
                                f.getId_funcionario(),
                                f.getNome_funcionario(),
                                f.getEmail()
                        )))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/criar")
    public ResponseEntity<FuncionarioCreateResponse> createFuncionario(@RequestBody FuncionarioRequestPayload payload, UriComponentsBuilder uriBuilder){
        Funcionario newFuncionario = new Funcionario(payload);

        var uri = uriBuilder.path("/funcionarios/{id}").buildAndExpand(newFuncionario.getId_funcionario()).toUri();

        // Codifica a senha antes de salvar o funcionário
        String encodedPassword = passwordEncoder.encode(payload.senha());
        newFuncionario.setSenha(encodedPassword);

        try {
            this.repository.save(newFuncionario);
            return ResponseEntity.created(uri).body(new FuncionarioCreateResponse(newFuncionario.getId_funcionario()));
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
            editadoFuncionario.setEmail(payload.email());

            // Codifica a senha antes de salvar o funcionário
            String encodedPassword = passwordEncoder.encode(payload.senha());
            editadoFuncionario.setSenha(encodedPassword);

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
