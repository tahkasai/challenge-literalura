package br.com.alura.literalura.Repository;

import br.com.alura.literalura.Model.Livro.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {
    Optional<LivroEntity> findByTituloIgnoreCase(String titulo);
    List<LivroEntity> findByIdiomaIgnoreCase(String idioma);
}
