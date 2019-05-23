package br.ufjf.dcc193.trabalho02douglasramon.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.dcc193.trabalho02douglasramon.Models.AreaConhecimento;;

public interface AreaConhecimentoRepository extends JpaRepository<AreaConhecimento, Long> {
}