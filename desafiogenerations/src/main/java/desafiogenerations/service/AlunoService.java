package desafiogenerations.service;

import desafiogenerations.exceptions.EmailJaCadastradoException;
import desafiogenerations.models.Aluno;
import desafiogenerations.repository.AlunoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Cacheable("alunos")
    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    @CacheEvict(value = "alunos", allEntries = true)
    public Aluno create(Aluno aluno) {
        try {
            alunoRepository.save(aluno);
            return aluno;
        } catch (DataIntegrityViolationException e) {
            throw new EmailJaCadastradoException("O e-mail já está cadastrado.");
        }
    }

    @CachePut(value = "alunos", key = "#id")
    public Aluno update(Long id, Aluno aluno) {
        Aluno alunoToUpdate = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        alunoToUpdate.setNome(aluno.getNome());
        alunoToUpdate.setEmail(aluno.getEmail());
        alunoToUpdate.setIdade(aluno.getIdade());
        alunoRepository.save(alunoToUpdate);
        return alunoToUpdate;
    }

    @CacheEvict(value = "alunos", key = "#id")
    public void delete(Long id) {
        Aluno alunoToDelete = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        alunoRepository.deleteById(id);
    }

    @Cacheable(value = "alunos", key = "#id")
    public Aluno findById(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }
}
