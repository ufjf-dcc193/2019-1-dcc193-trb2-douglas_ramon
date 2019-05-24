package br.ufjf.dcc193.trabalho02douglasramon.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.ufjf.dcc193.trabalho02douglasramon.Models.Trabalho;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.AreaConhecimentoRepository;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.RevisaoRepository;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.TrabalhoRepository;

@Controller
public class TrabalhoController {
    @Autowired
    TrabalhoRepository trabalhos;
    @Autowired
    AreaConhecimentoRepository areaConhecimentos;
    
    @RequestMapping("trabalho.html")
    public String trabalho(Model model) {
        model.addAttribute("trabalho", trabalhos.findAll());
        return "trabalho/trabalho";
    }

    @RequestMapping("formTrabalho.html")
    public String formTrabalho(Model model) {
        model.addAttribute("areaConhecimento", areaConhecimentos.findAll());
        return "trabalho/formTrabalho";
    }

    @RequestMapping("cadastrarTrabalho.html")
    public RedirectView cadastrarTrabalho(Trabalho trabalho) {
        trabalhos.save(trabalho);
        return new RedirectView("trabalho.html");
    }

    @RequestMapping("editarTrabalho.html")
    public ModelAndView editarTrabalho(Trabalho trabalho) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("trabalho", trabalhos.getOne(trabalho.getId()));
        mv.addObject("areaConhecimento", areaConhecimentos.findAll());
        mv.setViewName("trabalho/editarTrabalho");
        return mv;
    }

   
}