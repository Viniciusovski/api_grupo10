package desafiogenerations.repository;


import desafiogenerations.models.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

    public interface TurmaRepository extends JpaRepository <Turma, Long>{

}