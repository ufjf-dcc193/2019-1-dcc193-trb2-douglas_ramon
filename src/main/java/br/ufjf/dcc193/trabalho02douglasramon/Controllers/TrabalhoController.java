package br.ufjf.dcc193.trabalho02douglasramon.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.ufjf.dcc193.trabalho02douglasramon.Models.Trabalho;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.TrabalhoRepository;


@Controller
public class trabalhoController {
    @Autowired
    TrabalhoRepository trabalhos;

    @RequestMapping("trabalho.html")
    public String trabalho(Model model) {
        model.addAttribute("trabalho", trabalhos.findAll());
        return "trabalho/trabalho";
    }

    @RequestMapping("trabalho_form.html")
    public String trabalho_form(Model model) {
        return "trabalho/trabalho_form";
    }

    @RequestMapping("cadastrar_trabalho.html")
    public RedirectView cadastrar_trabalho(Trabalho trabalho) {
        trabalhos.save(trabalho);
        return new RedirectView("trabalho.html");
    }

    @RequestMapping("trabalho_editar.html")
    public ModelAndView carregar_trabalho_editar(Trabalho trabalho) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("trabalho", trabalhos.getOne(trabalho.getId()));
        mv.setViewName("trabalho/trabalho_editar");
        return mv;
    }

    @RequestMapping("trabalho_alterar.html")
    public RedirectView alterar(Trabalho trabalho) {
        trabalhos.save(trabalho);
        return new RedirectView("trabalho.html");
    }


}