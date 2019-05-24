package br.ufjf.dcc193.trabalho02douglasramon.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.ufjf.dcc193.trabalho02douglasramon.Models.AreaConhecimento;
import br.ufjf.dcc193.trabalho02douglasramon.Models.Avaliador;
import br.ufjf.dcc193.trabalho02douglasramon.Models.Trabalho;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.AreaConhecimentoRepository;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.AvaliadorRepository;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.TrabalhoRepository;

@Controller
public class AvaliadorController {
    @Autowired
    AvaliadorRepository avaliadores;
    @Autowired
    AreaConhecimentoRepository areaConhecimentos;
    @Autowired
    TrabalhoRepository trabalhos;

    @RequestMapping("avaliador.html")
    public String avaliador(Model model) {
        model.addAttribute("avaliador", avaliadores.findAll());
        return "avaliador/avaliador";
    }

    public String formAvaliador(Model model) {
        model.addAttribute("areaConhecimento", areaConhecimentos.findAll());
        return "avaliador/formAvaliador";
    }

    @RequestMapping("cadastrarAvaliador.html")
    public RedirectView cadastrarAvaliador(Avaliador avaliador) {
        avaliadores.save(avaliador);
        return new RedirectView("avaliador.html");
    }

    @RequestMapping("editarAvaliador.html")
    public ModelAndView editarAvaliador(Avaliador avaliador) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("avaliador", avaliadores.getOne(avaliador.getId()));
        mv.addObject("areaConhecimento", areaConhecimentos.findAll());
        mv.setViewName("avaliador/editarAvaliador");
        return mv;
    }

    /**
     * Requisito 6. Crie um conjunto de telas para o Avaliador se identificar no
     * sistema com seu e-mail ecódigo de acesso;
     * 
     * @param avaliador
     * @return true or false
     */
    public Boolean identificacaoSistema(Avaliador avaliador) {
        Avaliador aux = avaliadores.getOne(avaliador.getId());

        if (aux != null) {
            if (aux.getEmail() == avaliador.getEmail() && aux.getCodigo() == avaliador.getCodigo()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Requisito 7. Crie uma tela que lista as áreas de conhecimento que o Avaliador
     * pode atuar;
     * 
     * @param avaliador
     * @return areaConhecimento or null
     */
    public List<AreaConhecimento> areaConhecimentoAvaliador(Avaliador avaliador) {
        Avaliador aux = avaliadores.getOne(avaliador.getId());
        if (aux != null) {
            return aux.getAreaConhecimento();
        }
        return null;
    }

    /**
     * Requisito 8. Crie uma tela que, ao selecionar uma área de conhecimento do
     * Avaliador, liste os trabalhos da área, em ordem crescente com o número de
     * avaliações com status“avaliado”;
     * 
     * @param avaliador
     * @return listaTrabalhos or null
     */
    public List<Trabalho> trabalhosAreaConhecimento(Avaliador avaliador) {
        Avaliador aux = avaliadores.getOne(avaliador.getId());
        Trabalho trabalho;
        List<Trabalho> listaTrabalhos = new ArrayList<Trabalho>();

        if (aux != null) {
            for (int i = 0; i < aux.getAreaConhecimento().size(); i++) {
                trabalho = trabalhos.getOne(avaliador.getAreaConhecimento().get(i).getId());
                listaTrabalhos.add(trabalho);
            }
            return listaTrabalhos;
        }

        return null;
    }

}