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

/**
 *
 * @authors douglas e ramon
 */
@Controller
public class AvaliadorController {
    @Autowired
    AvaliadorRepository avaliadores;
    @Autowired
    AreaConhecimentoRepository areaConhecimentos;
    @Autowired
    TrabalhoRepository trabalhos;

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("avaliador.html")
    public String avaliador(Model model) {
        model.addAttribute("avaliador", avaliadores.findAll());
        return "avaliador/avaliador";
    }

    /**
     *
     * @return
     */
    @RequestMapping({"/avaliador-novo.html"})
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("avaliador/novo");
        mv.addObject("title", "Cadastrar Avaliador");
        return mv;
    }

    /**
     *
     * @param model
     * @return
     */
    public String formAvaliador(Model model) {
        model.addAttribute("areaConhecimento", areaConhecimentos.findAll());
        return "avaliador/formAvaliador";
    }

    /**
     *
     * @param avaliador
     * @return
     */
    @RequestMapping("cadastrarAvaliador.html")
    public RedirectView cadastrarAvaliador(Avaliador avaliador) {
        avaliadores.save(avaliador);
        return new RedirectView("avaliador.html");
    }

    /**
     *
     * @param avaliador
     * @return
     */
    @RequestMapping("editarAvaliador.html")
    public ModelAndView editarAvaliador(Avaliador avaliador) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("avaliador", avaliadores.getOne(avaliador.getId()));
        mv.addObject("areaConhecimento", areaConhecimentos.findAll());
        mv.setViewName("avaliador/editarAvaliador");
        return mv;
    }

    /**
     *
     * @param avaliador
     * @return true ou false
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
     *
     * @param avaliador
     * @return List<AreaConhecimento> ou null
     */
    public List<AreaConhecimento> getListaAreaConhecimentoAvaliador(Avaliador avaliador) {
        Avaliador aux = avaliadores.getOne(avaliador.getId());
        if (aux != null) {
            return aux.getAreaConhecimento();
        }
        return null;
    }

    /**
     *
     * @param avaliador
     * @return List<Trabalho> ou null
     */
    public List<Trabalho> getListaTrabalhosAreaConhecimentoAvaliador(Avaliador avaliador) {
        List<Trabalho> listaTrabalhos = new ArrayList<Trabalho>();
        Avaliador aux = avaliadores.getOne(avaliador.getId());
        Trabalho trabalho;

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