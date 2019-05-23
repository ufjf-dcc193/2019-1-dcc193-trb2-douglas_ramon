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
public class TrabalhoController {
    @Autowired
    TrabalhoRepository trabalhos;

    @RequestMapping("trabalho.html")
    public String trabalho(Model model) {
        model.addAttribute("trabalho", trabalhos.findAll());
        return "trabalho/trabalho";
    }

    @RequestMapping("trabalhoForm.html")
    public String trabalhoForm(Model model) {
        return "trabalho/trabalhoForm";
    }

    @RequestMapping("cadastrarTrabalho.html")
    public RedirectView cadastrarTrabalho(Trabalho trabalho) {
        trabalhos.save(trabalho);
        return new RedirectView("trabalho.html");
    }

    @RequestMapping("carregarTrabalhoEditar.html")
    public ModelAndView carregarTrabalhoEditar(Trabalho trabalho) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("trabalho", trabalhos.getOne(trabalho.getId()));
        mv.setViewName("trabalho/carregarTrabalhoEditar");
        return mv;
    }

    @RequestMapping("trabalhoAlterar.html")
    public RedirectView alterar(Trabalho trabalho) {
        trabalhos.save(trabalho);
        return new RedirectView("trabalho.html");
    }

}