package desafiogenerations.controller;

import desafiogenerations.models.Turma;
import desafiogenerations.service.TurmaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    public ResponseEntity<Turma> create(@RequestBody Turma turma) {
        Turma createdTurma = turmaService.create(turma);
        return new ResponseEntity<>(createdTurma, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Turma> list() {
        return turmaService.list();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> update(@PathVariable("id") Long id, @RequestBody Turma turma) {
        Turma updatedTurma = turmaService.update(id, turma);
        return new ResponseEntity<>(updatedTurma, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        turmaService.delete(id);
        return ResponseEntity.noContent().build(); 
    }
}
