package br.ufjf.dcc193.trabalho02douglasramon.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    TrabalhoRepository trabalhosRepository;
    @Autowired
    AreaConhecimentoRepository areaConhecimentosRepository;

    /**
     *
     * @param model
     * @return
     */
    @GetMapping("trabalho-listar.html")
    public ModelAndView trabalho() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("trabalhos", trabalhosRepository.findAll());
        mv.addObject("title", "Trabalhos");
        mv.setViewName("trabalho/listar");
        return mv;
    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping("/trabalho-novo.html")
    public ModelAndView formTrabalho() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("trabalho/novo");
        mv.addObject("areas", areaConhecimentosRepository.findAll());
        mv.addObject("title", "Trabalho");
        return mv;
    }

    /**
     *
     * @param trabalho
     * @return
     */
    @PostMapping({"/trabalho-novo.html", "/trabalho-editar.html"})
    public ModelAndView cadastrarTrabalho(@Valid Trabalho trabalho, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if(binding.hasErrors()){
            mv.setViewName("trabalho/editar");
            mv.addObject("trabalho", trabalho);
            mv.addObject("areas", areaConhecimentosRepository.findAll());
            mv.addObject("title", "Trabalho");
            return mv;
        } 
        trabalhosRepository.save(trabalho);
        mv.setViewName("redirect:trabalho-listar.html");
        return mv;
    }

    /**
     *
     * @param trabalho
     * @return
     */
    @GetMapping("/trabalho-editar.html")
    public ModelAndView editarTrabalho(Trabalho trabalho) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("trabalho", trabalhosRepository.getOne(trabalho.getId()));
        mv.addObject("areas", areaConhecimentosRepository.findAll());
        mv.setViewName("trabalho/editar");
        return mv;
    }
    
    @RequestMapping("/trabalho-excluir.html")
    public RedirectView remove(Trabalho trabalho) {
        trabalhosRepository.deleteById(trabalho.getId());
        return new RedirectView("trabalho-listar.html");
    }

}