package br.ufjf.dcc193.trabalho02douglasramon.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufjf.dcc193.trabalho02douglasramon.Models.Revisao;
import br.ufjf.dcc193.trabalho02douglasramon.Models.Trabalho;

/**
 *
 * @authors douglas e ramon
 */
public interface RevisaoRepository extends JpaRepository<Revisao, Long> {
    Revisao findByTrabalho(Trabalho trabalho);

    @Query("SELECT r FROM Revisao r WHERE r.trabalho.id = :id_trabalho AND r.avaliador.id = :id_avaliador")
    Revisao getRevisaoTrabalhoAvaliador(@Param("id_trabalho") Long id_trabalho, @Param("id_avaliador") Long id_avaliador);
}