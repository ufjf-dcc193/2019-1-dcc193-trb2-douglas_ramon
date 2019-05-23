package br.ufjf.dcc193.trabalho02douglasramon;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import br.ufjf.dcc193.trabalho02douglasramon.Models.AreaConhecimento;
import br.ufjf.dcc193.trabalho02douglasramon.Models.Avaliador;
import br.ufjf.dcc193.trabalho02douglasramon.Models.Revisao;
import br.ufjf.dcc193.trabalho02douglasramon.Models.Trabalho;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.AreaConhecimentoRepository;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.AvaliadorRepository;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.RevisaoRepository;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.TrabalhoRepository;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		// SpringApplication.run(MainApplication.class, args);
		ConfigurableApplicationContext ctx = SpringApplication.run(MainApplication.class, args);
		AreaConhecimentoRepository repositorioAreaConhecimento = ctx.getBean(AreaConhecimentoRepository.class);
		TrabalhoRepository repositorioTrabalho = ctx.getBean(TrabalhoRepository.class);
		AvaliadorRepository repositorioAvaliador = ctx.getBean(AvaliadorRepository.class);
		RevisaoRepository repositorioRevisao = ctx.getBean(RevisaoRepository.class);

		AreaConhecimento ac1 = new AreaConhecimento("Descrição 1");
		AreaConhecimento ac2 = new AreaConhecimento("Descrição 2");
		AreaConhecimento ac3 = new AreaConhecimento("Descrição 3");
		repositorioAreaConhecimento.save(ac1);
		repositorioAreaConhecimento.save(ac2);
		repositorioAreaConhecimento.save(ac3);

		Trabalho t1 = new Trabalho("Título 1", "Descricao 1", "URL 1", ac1);
		Trabalho t2 = new Trabalho("Título 2", "Descricao 2", "URL 2", ac2);
		Trabalho t3 = new Trabalho("Título 3", "Descricao 3", "URL 3", ac3);
		repositorioTrabalho.save(t1);
		repositorioTrabalho.save(t2);
		repositorioTrabalho.save(t3);

		List<AreaConhecimento> listAC1 = new ArrayList<>();
		listAC1.add(ac1);
		listAC1.add(ac2);
		listAC1.add(ac3);

		Avaliador a1 = new Avaliador("Nome 1", "Email 1", 1, listAC1);
		Avaliador a2 = new Avaliador("Nome 2", "Email 2", 2, listAC1);
		Avaliador a3 = new Avaliador("Nome 3", "Email 3", 3, listAC1);
		repositorioAvaliador.save(a1);
		repositorioAvaliador.save(a2);
		repositorioAvaliador.save(a3);

		Revisao r1 = new Revisao(1, "Descrição 1", "Opção 1", t1, a1);
		Revisao r2 = new Revisao(2, "Descrição 2", "Opção 2", t2, a2);
		Revisao r3 = new Revisao(3, "Descrição 3", "Opção 3", t3, a3);
		repositorioRevisao.save(r1);
		repositorioRevisao.save(r2);
		repositorioRevisao.save(r3);

	}

}
