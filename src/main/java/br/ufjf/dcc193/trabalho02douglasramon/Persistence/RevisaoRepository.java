package br.ufjf.dcc193.trabalho02douglasramon.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.dcc193.trabalho02douglasramon.Models.Revisao;

/**
 *
 * @authors douglas e ramon
 */
public interface RevisaoRepository extends JpaRepository<Revisao, Long> {
    
}