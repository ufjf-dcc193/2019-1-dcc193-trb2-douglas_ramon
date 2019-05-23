package br.ufjf.dcc193.trabalho02douglasramon.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.ufjf.dcc193.trabalho02douglasramon.Models.Revisao;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.RevisaoRepository;


@Controller
public class revisaoController {
    @Autowired
    RevisaoRepository revisoes;

    @RequestMapping("revisao.html")
    public String revisao(Model model) {
        model.addAttribute("revisao", revisoes.findAll());
        return "revisao/revisao";
    }

    @RequestMapping("revisao_form.html")
    public String revisao_form(Model model) {
        return "revisao/revisao_form";
    }

    @RequestMapping("cadastrar_revisao.html")
    public RedirectView cadastrar_revisao(Revisao revisao) {
        revisoes.save(revisao);
        return new RedirectView("revisao.html");
    }

    @RequestMapping("revisao_editar.html")
    public ModelAndView carregar_sede_editar(Revisao revisao) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("revisao", revisoes.getOne(revisao.getId()));
        mv.setViewName("revisao/revisao_editar");
        return mv;
    }

    @RequestMapping("revisao_alterar.html")
    public RedirectView alterar(Revisao revisao) {
        revisoes.save(revisao);
        return new RedirectView("revisao.html");
    }


}