package br.ufjf.dcc193.trabalho02douglasramon.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.ufjf.dcc193.trabalho02douglasramon.Models.Trabalho;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.AreaConhecimentoRepository;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.TrabalhoRepository;

/**
 *
 * @authors douglas e ramon
 */
@Controller
public class TrabalhoController {
    @Autowired
    TrabalhoRepository trabalhos;
    @Autowired
    AreaConhecimentoRepository areaConhecimentos;

    /**
     *
     * @param model
     * @return
     */
    @GetMapping("trabalho-listar.html")
    public ModelAndView trabalho() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("trabalhos", trabalhos.findAll());
        mv.addObject("title", "Trabalhos");
        mv.setViewName("trabalho/listar");
        return mv;
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("formTrabalho.html")
    public String formTrabalho(Model model) {
        model.addAttribute("areaConhecimento", areaConhecimentos.findAll());
        return "trabalho/formTrabalho";
    }

    /**
     *
     * @param trabalho
     * @return
     */
    @RequestMapping("cadastrarTrabalho.html")
    public RedirectView cadastrarTrabalho(Trabalho trabalho) {
        trabalhos.save(trabalho);
        return new RedirectView("trabalho.html");
    }

    /**
     *
     * @param trabalho
     * @return
     */
    @RequestMapping("editarTrabalho.html")
    public ModelAndView editarTrabalho(Trabalho trabalho) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("trabalho", trabalhos.getOne(trabalho.getId()));
        mv.addObject("areaConhecimento", areaConhecimentos.findAll());
        mv.setViewName("trabalho/editarTrabalho");
        return mv;
    }

    @GetMapping("/trabalho-novo.html")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("trabalho/novo");
        mv.addObject("areas", areaConhecimentos.findAll());
        mv.addObject("title", "Trabalho");
        return mv;
    }

}