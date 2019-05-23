package br.ufjf.dcc193.trabalho02douglasramon.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.ufjf.dcc193.trabalho02douglasramon.Models.Avaliador;
import br.ufjf.dcc193.trabalho02douglasramon.Models.Sede;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.AvaliadorRepository;


@Controller
public class AvaliadorController {
    @Autowired
    AvaliadorRepository avaliadores;

    @RequestMapping("avaliador.html")
    public String avaliador(Model model) {
        model.addAttribute("avaliador", avaliadores.findAll());
        return "avaliador/avaliador";
    }

    @RequestMapping("avaliador_form.html")
    public String avaliador_form(Model model) {
        return "avaliador/avaliador_form";
    }

    @RequestMapping("cadastrar_avaliador.html")
    public RedirectView cadastrar_avaliador(Avaliador avaliador) {
        avaliadores.save(avaliador);
        return new RedirectView("avaliador.html");
    }

    @RequestMapping("avaliador_editar.html")
    public ModelAndView carregar_sede_editar(Avaliador avaliador) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("avaliador", avaliadores.getOne(avaliador.getId()));
        mv.setViewName("avaliador/avaliador_editar");
        return mv;
    }

    @RequestMapping("avaliador_alterar.html")
    public RedirectView alterar(Avaliador avaliador) {
        avaliadores.save(avaliador);
        return new RedirectView("avaliador.html");
    }


}