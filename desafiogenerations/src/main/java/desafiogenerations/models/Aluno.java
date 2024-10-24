package desafiogenerations.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome deve ser preenchido")
    @Column(nullable = false, unique = false, length = 512)
    private String nome;

    @NotNull(message = "O email não pode ser nulo")
    @NotBlank(message = "O email deve ser preenchido")
    @Column(nullable = false, unique = true, length = 512)
    private String email;

    //Somente valores positivos
    @NotNull(message = "A idade não pode ser nula")
    @Min(value = 1, message = "A idade deve ser um valor positivo")
    @Column(nullable = false)
    private Integer idade;

    @NotNull(message = "A nota do primeiro semestre não pode ser nula")
    @Min(value = 0, message = "A nota do primeiro semestre deve ser no mínimo 0")
    @Max(value = 10, message = "A nota do primeiro semestre deve ser no máximo 10")
    private Double notaPrimeiroSemestre;

    @NotNull(message = "A nota do segundo semestre não pode ser nula")
    @Min(value = 0, message = "A nota do segundo semestre deve ser no mínimo 0")
    @Max(value = 10, message = "A nota do segundo semestre deve ser no máximo 10")
    private Double notaSegundoSemestre;

    public Aluno(Long id, String nome, String email, Integer idade, Double notaPrimeiroSemestre, Double notaSegundoSemestre, Turma turma, String nomeProfessor, Integer numeroSala) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.notaPrimeiroSemestre = notaPrimeiroSemestre;
        this.notaSegundoSemestre = notaSegundoSemestre;
        this.turma = turma;
        this.nomeProfessor = nomeProfessor;
        this.numeroSala = numeroSala;
    }

    public Aluno(long l, String johnDoe, String email, int i, int i1) {

    }

    public Aluno() {

    }

    public Double getMedia() {
        if (notaPrimeiroSemestre != null && notaSegundoSemestre != null) {
            return (notaPrimeiroSemestre + notaSegundoSemestre) / 2;
        }
        return null; // Retorna null se uma das notas for nula
    }

    //Relaçao com turma
    @ManyToOne(fetch = FetchType.LAZY)  // Define o tipo de carregamento como LAZY
    @JoinColumn(name = "turma_id")  // Especifica o nome da coluna que fará o join na tabela "turma"
    @JsonIgnoreProperties("alunos")
    private Turma turma;

    private String nomeProfessor;

    private Integer numeroSala;

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getNotaPrimeiroSemestre() {
        return notaPrimeiroSemestre;
    }

    public void setNotaPrimeiroSemestre(Double notaPrimeiroSemestre) {
        this.notaPrimeiroSemestre = notaPrimeiroSemestre;
    }

    public @NotNull(message = "O email não pode ser nulo") @NotBlank(message = "O email deve ser preenchido") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "O email não pode ser nulo") @NotBlank(message = "O email deve ser preenchido") String email) {
        this.email = email;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Double getNotaSegundoSemestre() {
        return notaSegundoSemestre;
    }

    public void setNotaSegundoSemestre(Double notaSegundoSemestre) {
        this.notaSegundoSemestre = notaSegundoSemestre;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public Integer getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(Integer numeroSala) {
        this.numeroSala = numeroSala;
    }
}
