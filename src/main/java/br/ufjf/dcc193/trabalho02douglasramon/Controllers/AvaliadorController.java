package br.ufjf.dcc193.trabalho02douglasramon.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.ufjf.dcc193.trabalho02douglasramon.Models.Avaliador;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.AreaConhecimentoRepository;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.AvaliadorRepository;

@Controller
public class AvaliadorController {
    @Autowired
    AvaliadorRepository avaliadores;
    @Autowired
    AreaConhecimentoRepository areaConhecimentos;

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

}