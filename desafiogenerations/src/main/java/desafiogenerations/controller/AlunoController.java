package desafiogenerations.controller;

import desafiogenerations.models.Aluno;
import desafiogenerations.service.AlunoService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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

    // Helper method to centralize HATEOAS links
    private EntityModel<Aluno> toModel(Aluno aluno) {
        EntityModel<Aluno> alunoModel = EntityModel.of(aluno);
        alunoModel.add(linkTo(methodOn(AlunoController.class).findById(aluno.getId())).withSelfRel());
        alunoModel.add(linkTo(methodOn(AlunoController.class).list()).withRel("alunos"));
        alunoModel.add(linkTo(methodOn(AlunoController.class).update(aluno.getId(), aluno)).withRel("update"));
        alunoModel.add(linkTo(methodOn(AlunoController.class).delete(aluno.getId())).withRel("delete"));
        return alunoModel;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Aluno>> create(@RequestBody Aluno aluno) {
        Aluno createdAluno = alunoService.create(aluno);
        return ResponseEntity.ok(toModel(createdAluno));
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Aluno>>> list() {
        List<EntityModel<Aluno>> alunos = alunoService.findAll().stream()
                .map(this::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Aluno>> collectionModel = CollectionModel.of(alunos);
        collectionModel.add(linkTo(methodOn(AlunoController.class).list()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Aluno>> findById(@PathVariable Long id) {
        Aluno aluno = alunoService.findById(id);
        return ResponseEntity.ok(toModel(aluno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Aluno>> update(@PathVariable Long id, @Valid @RequestBody Aluno aluno) {
        Aluno updatedAluno = alunoService.update(id, aluno);
        return ResponseEntity.ok(toModel(updatedAluno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
