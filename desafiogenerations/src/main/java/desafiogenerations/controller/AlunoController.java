package desafiogenerations.controller;

import desafiogenerations.models.Aluno;
import desafiogenerations.service.AlunoService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Aluno>> create(@RequestBody Aluno aluno) {
        Aluno createdAluno = alunoService.create(aluno);

        EntityModel<Aluno> alunoModel = EntityModel.of(createdAluno);
        alunoModel.add(linkTo(methodOn(AlunoController.class).list()).withRel("alunos"));
        alunoModel.add(linkTo(methodOn(AlunoController.class).update(createdAluno.getId(), aluno)).withRel("update"));
        alunoModel.add(linkTo(methodOn(AlunoController.class).delete(createdAluno.getId())).withRel("delete"));

        return ResponseEntity.ok(alunoModel);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<Aluno>>> list() {
        List<EntityModel<Aluno>> alunos = alunoService.list().stream()
                .map(aluno -> {
                    EntityModel<Aluno> alunoModel = EntityModel.of(aluno);
                    alunoModel.add(linkTo(methodOn(AlunoController.class).update(aluno.getId(), aluno)).withRel("update"));
                    alunoModel.add(linkTo(methodOn(AlunoController.class).delete(aluno.getId())).withRel("delete"));
                    return alunoModel;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(alunos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Aluno>> update(@PathVariable Long id, @Valid @RequestBody Aluno aluno) {
        Aluno updatedAluno = alunoService.update(id, aluno);

        EntityModel<Aluno> alunoModel = EntityModel.of(updatedAluno);
        alunoModel.add(linkTo(methodOn(AlunoController.class).list()).withRel("alunos"));
        alunoModel.add(linkTo(methodOn(AlunoController.class).delete(updatedAluno.getId())).withRel("delete"));

        return ResponseEntity.ok(alunoModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}