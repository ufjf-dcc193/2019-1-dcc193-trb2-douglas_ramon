package br.ufjf.dcc193.trabalho02douglasramon.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.ufjf.dcc193.trabalho02douglasramon.Models.AreaConhecimento;
import br.ufjf.dcc193.trabalho02douglasramon.Models.Avaliador;
import br.ufjf.dcc193.trabalho02douglasramon.Models.Trabalho;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.AreaConhecimentoRepository;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.AvaliadorRepository;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.TrabalhoRepository;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @authors douglas e ramon
 */
@Controller
public class AreaController {
    @Autowired
    AreaConhecimentoRepository areaConhecimentos;

    @RequestMapping(value = "/area-nova.html", method = RequestMethod.GET)
    public ModelAndView nova() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("area/nova");
        mv.addObject("title", "Nova Área de Conhecimento");
        return mv;
    }

    @RequestMapping(value = "/area-nova.html", method = RequestMethod.POST)
    public ModelAndView salvar(AreaConhecimento ac) {
        ModelAndView mv = new ModelAndView();
        areaConhecimentos.save(ac);
        mv.setViewName("area/listar");
        List<AreaConhecimento> areas = new ArrayList<>();
        areas = areaConhecimentos.findAll();
        mv.addObject("areas", areas);
        mv.addObject("title", "Lista de Áreas");
        return mv;
    }

    @RequestMapping("/area-listar.html")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("area/listar");
        List<AreaConhecimento> areas = new ArrayList<>();
        areas = areaConhecimentos.findAll();
        mv.addObject("areas", areas);
        mv.addObject("title", "Lista de Áreas");
        return mv;
    }
    

}