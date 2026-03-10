package br.com.alura.literalura.Model.Livro;

import br.com.alura.literalura.Model.Autor.AutorEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String idioma;
    private Integer downloads;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "autor_id")
    private AutorEntity autor;

    public LivroEntity() {}

    public LivroEntity(Livro dto) {
        this.titulo = dto.titulo();
        this.idioma = (dto.linguagem() != null && !dto.linguagem().isEmpty())
                ? dto.linguagem().get(0) : "desconhecido";
        this.downloads = dto.download();
        if (dto.autor() != null && !dto.autor().isEmpty()) {
            this.autor = new AutorEntity(dto.autor().get(0));
        }
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getIdioma() { return idioma; }
    public Integer getDownloads() { return downloads; }
    public AutorEntity getAutor() { return autor; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public void setDownloads(Integer downloads) { this.downloads = downloads; }
    public void setAutor(AutorEntity autor) { this.autor = autor; }

    @Override
    public String toString() {
        return """
                ----------- LIVRO -----------
                Título: %s
                Autor: %s
                Idioma: %s
                Downloads: %d
                ----------------------------""".formatted(
                titulo,
                autor != null ? autor.getNome() : "Desconhecido",
                idioma,
                downloads
        );
    }
}
