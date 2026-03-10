package br.com.alura.literalura.Model;

import br.com.alura.literalura.Model.Livro.Livro;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Resposta(
        @JsonAlias("results") List<Livro> resultados
) {
}
