package br.ufjf.dcc193.trabalho02douglasramon.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.ufjf.dcc193.trabalho02douglasramon.Models.AreaConhecimento;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.AreaConhecimentoRepository;

/**
 *
 * @authors douglas e ramon
 */
@Controller
public class AreaController {
    @Autowired
    AreaConhecimentoRepository areaConhecimentos;

    @GetMapping("/area-nova.html")
    public ModelAndView nova() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("area/nova");
        mv.addObject("title", "Área de Conhecimento");
        return mv;
    }

    @PostMapping({"/area-nova.html", "/area-editar.html"})
    public ModelAndView salvar(@Valid AreaConhecimento ac, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if(binding.hasErrors()){
            mv.setViewName("area/editar");
            mv.addObject("area", ac);
            mv.addObject("title", "Área de Conhecimento");
            return mv;
        } 
        areaConhecimentos.save(ac);
        mv.setViewName("redirect:area-listar.html");
        return mv;
    }

    @RequestMapping("/area-listar.html")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("area/listar");
        List<AreaConhecimento> areas = areaConhecimentos.findAll();
        mv.addObject("areas", areas);
        mv.addObject("title", "Lista de Áreas");
        return mv;
    }
    
    @RequestMapping("/area-excluir.html")
    public RedirectView remove(AreaConhecimento ac) {
        areaConhecimentos.deleteById(ac.getId());
        return new RedirectView("area-listar.html");
    }

    @GetMapping("/area-editar.html")
    public ModelAndView editar(AreaConhecimento ac) {
        ModelAndView mv = new ModelAndView();
        AreaConhecimento area = areaConhecimentos.getOne(ac.getId());
        mv.setViewName("area/editar");
        mv.addObject("title", "Área de Conhecimento");
        mv.addObject("area", area);
        return mv;
    }
    
}