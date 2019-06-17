package br.ufjf.dcc193.trabalho02douglasramon.Persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufjf.dcc193.trabalho02douglasramon.Models.Trabalho;

/**
 *
 * @authors douglas e ramon
 */
public interface TrabalhoRepository extends JpaRepository<Trabalho, Long> {
    @Query("SELECT t FROM Trabalho t WHERE t.areaConhecimento.id = :id")
    List<Trabalho> listaTrabalhoByArea(@Param("id") Long id);
}