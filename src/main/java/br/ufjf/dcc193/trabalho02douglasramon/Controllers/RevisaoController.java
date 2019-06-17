package br.ufjf.dcc193.trabalho02douglasramon.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.ufjf.dcc193.trabalho02douglasramon.Models.Revisao;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.AvaliadorRepository;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.RevisaoRepository;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.TrabalhoRepository;

/**
 *
 * @authors douglas e ramon
 */
@Controller
public class RevisaoController {
    @Autowired
    RevisaoRepository revisoes;
    @Autowired
    AvaliadorRepository avaliadores;
    @Autowired
    TrabalhoRepository trabalhos;

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("revisao.html")
    public String revisao(Model model) {
        model.addAttribute("revisao", revisoes.findAll());
        return "revisao/revisao";
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("formRevisao.html")
    public String formRevisao(Model model) {
        model.addAttribute("avaliador", avaliadores.findAll());
        model.addAttribute("trabalho", trabalhos.findAll());
        return "revisao/formRevisao";
    }

    /**
     *
     * @param revisao
     * @return
     */
    @RequestMapping("cadastrarRevisao.html")
    public RedirectView cadastrarRevisao(Revisao revisao) {
        revisoes.save(revisao);
        return new RedirectView("revisao.html");
    }

    /**
     *
     * @param revisao
     * @return
     */
    @RequestMapping("editarRevisao.html")
    public ModelAndView editarRevisao(Revisao revisao) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("revisao", revisoes.getOne(revisao.getId()));
        mv.addObject("avaliador", avaliadores.findAll());
        mv.addObject("trabalho", trabalhos.findAll());
        mv.setViewName("revisao/editarRevisao");
        return mv;
    }

    /**
     *
     * @param revisao
     * @return
     */
    @RequestMapping("realizarRevisao.html")
    public ModelAndView realizarRevisao(Revisao revisao) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("trabalho", revisoes.getOne(revisao.getTrabalho().getId()));
        mv.setViewName("trabalho/realizarRevisao");
        return mv;
    }

    /**
     *
     * @param revisao
     * @return
     */
    // @RequestMapping("revisarDepois.html")
    // public ModelAndView revisarDepois(Revisao revisao) {
    //     ModelAndView mv = new ModelAndView();
    //     Revisao aux = revisoes.getOne(revisao.getId());
    //     aux.setStatus("A fazer");
    //     revisoes.save(aux);
    //     mv.setViewName("trabalho/revisarDepois");
    //     return mv;
    // }

    /**
     *
     * @param revisao
     * @return
     */
    // @RequestMapping("revisarAgora.html")
    // public ModelAndView revisarAgora(Revisao revisao) {
    //     ModelAndView mv = new ModelAndView();
    //     Revisao aux = revisoes.getOne(revisao.getId());
    //     aux.setStatus("Avaliado");
    //     revisoes.save(aux);
    //     mv.setViewName("trabalho/revisarAgora");
    //     return mv;
    // }

    /**
     *
     * @param revisao
     * @return
     */
    // @RequestMapping("pular.html")
    // public ModelAndView pular(Revisao revisao) {
    //     ModelAndView mv = new ModelAndView();
    //     Revisao aux = revisoes.getOne(revisao.getId());
    //     aux.setStatus("Impedido");
    //     revisoes.save(aux);
    //     mv.setViewName("trabalho/pular");
    //     return mv;
    // }

    /**
     *
     * @param revisao
     * @return
     */
    @RequestMapping("listarRevisoesAvaliador.html")
    public ModelAndView listarRevisoesAvaliador(Revisao revisao) {
        ModelAndView mv = new ModelAndView();
        if (revisao.getStatus() == "Avaliado") {
            mv.addObject("revisao", revisoes.getOne(revisao.getId()));
        }
        mv.setViewName("trabalho/listarRevisoesAvaliador");
        return mv;
    }

    /**
     *
     * @param revisao
     * @return
     */
    @RequestMapping("alterarStatusRevisoesAvaliador.html")
    public ModelAndView alterarStatusRevisoesAvaliador(Revisao revisao) {
        ModelAndView mv = new ModelAndView();
        Revisao aux = revisoes.getOne(revisao.getId());
        aux.setStatus(revisao.getStatus());
        revisoes.save(aux);
        mv.setViewName("trabalho/alterarStatusRevisoesAvaliador");
        return mv;
    }

}