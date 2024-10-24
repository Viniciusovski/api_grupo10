package desafiogenerations.service;

import desafiogenerations.models.Turma;
import desafiogenerations.repository.TurmaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    // Cria uma nova turma e retorna a turma criada
    public Turma create(Turma turma) {
        return turmaRepository.save(turma);
    }

    // Retorna todas as turmas
    public List<Turma> list() {
        return turmaRepository.findAll();
    }

    // Atualiza uma turma existente e retorna a turma atualizada
    public Turma update(Long id, Turma turma) {
        Turma turmaToUpdate = turmaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com id: " + id));

        turmaToUpdate.setNome(turma.getNome());
        turmaToUpdate.setInstrutor(turma.getInstrutor());

        return turmaRepository.save(turmaToUpdate);
    }

    // Deleta uma turma e verifica se existe
    public void delete(Long id) {
        if (!turmaRepository.existsById(id)) {
            throw new EntityNotFoundException("Turma não encontrada com id: " + id);
        }
        turmaRepository.deleteById(id);
    }
}
