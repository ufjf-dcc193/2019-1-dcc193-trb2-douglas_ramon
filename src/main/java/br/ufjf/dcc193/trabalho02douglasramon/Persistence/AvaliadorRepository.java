package br.ufjf.dcc193.trabalho02douglasramon.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.dcc193.trabalho02douglasramon.Models.Avaliador;

public interface AvaliadorRepository extends JpaRepository<Avaliador, Long> {
}