package br.ufjf.dcc193.trabalho02douglasramon.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.dcc193.trabalho02douglasramon.Models.Trabalho;

public interface TrabalhoRepository extends JpaRepository<Trabalho, Long> {
}