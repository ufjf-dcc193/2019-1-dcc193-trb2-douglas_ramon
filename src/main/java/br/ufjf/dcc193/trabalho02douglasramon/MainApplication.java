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

/**
 *
 * @authors douglas e ramon
 */
@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		// SpringApplication.run(MainApplication.class, args);
		System.out.println("<<< Iniciando Sistema >>>");
		ConfigurableApplicationContext ctx;
		AreaConhecimentoRepository repositorioAreaConhecimento;
		TrabalhoRepository repositorioTrabalho;
		AvaliadorRepository repositorioAvaliador;
		RevisaoRepository repositorioRevisao;

		List<AreaConhecimento> listAC1;
		List<AreaConhecimento> listAC2;

		AreaConhecimento ac1;
		AreaConhecimento ac2;
		AreaConhecimento ac3;
		AreaConhecimento ac4;

		Trabalho t1;
		Trabalho t2;
		Trabalho t3;

		Avaliador a1;
		Avaliador a2;
		Avaliador a3;

		Revisao r1;
		Revisao r2;
		Revisao r3;

		ctx = SpringApplication.run(MainApplication.class, args);
		repositorioAreaConhecimento = ctx.getBean(AreaConhecimentoRepository.class);
		repositorioTrabalho = ctx.getBean(TrabalhoRepository.class);
		repositorioAvaliador = ctx.getBean(AvaliadorRepository.class);
		repositorioRevisao = ctx.getBean(RevisaoRepository.class);

		ac1 = new AreaConhecimento("Descrição 1");
		ac2 = new AreaConhecimento("Descrição 2");
		ac3 = new AreaConhecimento("Descrição 3");
		ac4 = new AreaConhecimento("Descrição 4");
		// repositorioAreaConhecimento.save(ac1);
		// repositorioAreaConhecimento.save(ac2);
		// repositorioAreaConhecimento.save(ac3);
		// repositorioAreaConhecimento.save(ac4);

		listAC1 = new ArrayList<AreaConhecimento>();
		// listAC1.add(ac1);
		// listAC1.add(ac2);
		// listAC1.add(ac3);

		listAC2 = new ArrayList<AreaConhecimento>();
		// listAC2.add(ac1);
		// listAC2.add(ac4);

		t1 = new Trabalho("Título 1", "Descricao 1", "URL 1", ac1);
		t2 = new Trabalho("Título 2", "Descricao 2", "URL 2", ac2);
		t3 = new Trabalho("Título 3", "Descricao 3", "URL 3", ac3);
		// repositorioTrabalho.save(t1);
		// repositorioTrabalho.save(t2);
		// repositorioTrabalho.save(t3);

		a1 = new Avaliador("Nome 1", "email1@gmail.com", "1", listAC1);
		a2 = new Avaliador("Nome 2", "email2@gmail.com", "2");
		a3 = new Avaliador("Nome 3", "email3@gmail.com", "3", listAC2);
		//repositorioAvaliador.save(a1);
		//repositorioAvaliador.save(a2);
		//repositorioAvaliador.save(a3);

		r1 = new Revisao(1, "Descrição 1", "Opção 1", t1, a1);
		r2 = new Revisao(2, "Descrição 2", "Opção 2", t2, a2);
		r3 = new Revisao(3, "Descrição 3", "Opção 3", t3, a3);
		// repositorioRevisao.save(r1);
		// repositorioRevisao.save(r2);
		// repositorioRevisao.save(r3);
	}

}
