package br.com.alura.literalura.Repository;

import br.com.alura.literalura.Model.Autor.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<AutorEntity, Long> {
    Optional<AutorEntity> findByNome(String nome);
    List<AutorEntity> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(Integer ano, Integer ano2);
}
