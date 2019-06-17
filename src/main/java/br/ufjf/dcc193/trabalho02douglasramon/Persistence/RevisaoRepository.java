package br.ufjf.dcc193.trabalho02douglasramon.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.dcc193.trabalho02douglasramon.Models.Revisao;
import br.ufjf.dcc193.trabalho02douglasramon.Models.Trabalho;

/**
 *
 * @authors douglas e ramon
 */
public interface RevisaoRepository extends JpaRepository<Revisao, Long> {
    public Revisao findByTrabalho(Trabalho trabalho);
}