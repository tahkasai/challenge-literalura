package br.com.alura.literalura.Model.Livro;

import br.com.alura.literalura.Model.Autor.Autor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Livro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<Autor> autor,
        @JsonAlias("languages") List<String> linguagem,
        @JsonAlias("download_count") Integer download
){}
