package desafiogenerations.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"nome"})})
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long turma_id;

    private String nome;
    private String instrutor;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)  // 'turma' refere-se ao campo em 'Aluno'
    @JsonIgnoreProperties("turma")
    private List<Aluno> alunos;

    // Getters e Setters
    public Long getId() {
        return turma_id;
    }

    public void setId(Long id) {
        this.turma_id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(String instrutor) {
        this.instrutor = instrutor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}