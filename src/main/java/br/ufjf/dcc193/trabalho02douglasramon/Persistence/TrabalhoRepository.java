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
    @Query("SELECT t FROM Trabalho t JOIN Revisao r ON t.id = r.trabalho.id WHERE t.areaConhecimento.id = :id AND r.status = 'Avaliado' OR r.status = 'Impedido' OR r.status = 'Invalidado' OR r.status = 'Validado'")
    List<Trabalho> listaTrabalhosNaoAvaliados(@Param("id") Long id);
    @Query("SELECT t FROM Trabalho t JOIN Revisao r ON t.id = r.trabalho.id JOIN Avaliador a ON a.id = r.avaliador.id WHERE a.id = :id_avaliador")
    List<Trabalho> listaTrabalhosAvaliador(@Param("id_avaliador") Long id);
}