package desafiogenerations.service;

import desafiogenerations.models.Aluno;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import desafiogenerations.repository.AlunoRepository;

import java.util.List;


@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Cacheable("alunos")
    public List<Aluno> create(Aluno aluno) {
        alunoRepository.save(aluno);
        return list();
    }

    @Cacheable("alunos")
    public List<Aluno> list() {
        return alunoRepository.findAll();
    }

    @CachePut("alunos")
    public List<Aluno> update(Long id, Aluno aluno) {
        Aluno alunoToUpdate = alunoRepository.findById(id).orElseThrow();
        alunoToUpdate.setNome(aluno.getNome());
        alunoRepository.save(alunoToUpdate);
        return list();
    }


    public List<Aluno> delete(Long id) {
        alunoRepository.deleteById(id);
        return list();
    }
}