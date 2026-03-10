package br.com.alura.literalura.Model.Autor;

import br.com.alura.literalura.Model.Livro.LivroEntity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class AutorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LivroEntity> livros = new ArrayList<>();

    public AutorEntity() {}

    public AutorEntity(Autor dto) {
        this.nome = dto.autor();
        this.anoNascimento = dto.anoNascimento();
        this.anoFalecimento = dto.anoFalecimento();
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Integer getAnoNascimento() { return anoNascimento; }
    public Integer getAnoFalecimento() { return anoFalecimento; }
    public List<LivroEntity> getLivros() { return livros; }

    public void setNome(String nome) { this.nome = nome; }
    public void setAnoNascimento(Integer anoNascimento) { this.anoNascimento = anoNascimento; }
    public void setAnoFalecimento(Integer anoFalecimento) { this.anoFalecimento = anoFalecimento; }
    public void setLivros(List<LivroEntity> livros) { this.livros = livros; }

    @Override
    public String toString() {
        return "Autor: " + nome + "\n" +
                "Ano de nascimento: " + (anoNascimento != null ? anoNascimento : "?") + "\n" +
                "Ano de falecimento: " + (anoFalecimento != null ? anoFalecimento : "?");
    }
}
