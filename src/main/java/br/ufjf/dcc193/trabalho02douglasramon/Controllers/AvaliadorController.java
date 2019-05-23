package br.ufjf.dcc193.trabalho02douglasramon.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.ufjf.dcc193.trabalho02douglasramon.Models.Avaliador;
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
    public String avaliadorForm(Model model) {
        return "avaliador/avaliadorForm";
    }

    @RequestMapping("cadastrarAvaliador.html")
    public RedirectView cadastrarAvaliador(Avaliador avaliador) {
        avaliadores.save(avaliador);
        return new RedirectView("avaliador.html");
    }

    @RequestMapping("carregarAvaliadorEditar.html")
    public ModelAndView carregarAvaliadorEditar(Avaliador avaliador) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("avaliador", avaliadores.getOne(avaliador.getId()));
        mv.setViewName("avaliador/carregarAvaliadorEditar");
        return mv;
    }

    @RequestMapping("avaliadorAlterar.html")
    public RedirectView alterar(Avaliador avaliador) {
        avaliadores.save(avaliador);
        return new RedirectView("avaliador.html");
    }


}