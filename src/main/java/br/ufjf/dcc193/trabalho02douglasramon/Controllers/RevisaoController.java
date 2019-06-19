package br.ufjf.dcc193.trabalho02douglasramon.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    RevisaoRepository revisoesRepository;
    @Autowired
    AvaliadorRepository avaliadoresRepository;
    @Autowired
    TrabalhoRepository trabalhosRepository;

    /**
     *
     * @param model
     * @return
     */
    @GetMapping("revisao-listar.html")
    public ModelAndView revisao() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("revisoes", revisoesRepository.findAllByOrderByAvaliadorAsc());
        mv.addObject("title", "Lista de Revisões");
        mv.addObject("editar", "false");
        mv.setViewName("revisoes/listar");
        return mv;
    }

    @PostMapping("revisao-listar.html")
    public ModelAndView revisao(Revisao revisao) {
        ModelAndView mv = new ModelAndView();
        Revisao r = revisoesRepository.getOne(revisao.getId());
        mv.addObject("revisoes", revisoesRepository.findAllByOrderByAvaliadorAsc());
        mv.addObject("id_revisar", r.getId());
        mv.addObject("title", "Lista de Revisões");
        mv.addObject("editar", "true");
        mv.setViewName("revisoes/listar");
        return mv;
    }

    @PostMapping("salvar-revisao-lista.html")
    public ModelAndView salvar(Revisao revisao) {
        ModelAndView mv = new ModelAndView();
        Revisao aux = revisoesRepository.getOne(revisao.getId());
        Revisao r = new Revisao(aux.getNota(), aux.getDescricao(), revisao.getStatus(), aux.getTrabalho(), aux.getAvaliador());
        r.setId(aux.getId());
        revisoesRepository.save(r);
        mv.setViewName("redirect:revisao-listar.html");
        return mv;
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("formRevisao.html")
    public String formRevisao(Model model) {
        model.addAttribute("avaliador", avaliadoresRepository.findAll());
        model.addAttribute("trabalho", trabalhosRepository.findAll());
        return "revisao/formRevisao";
    }

    /**
     *
     * @param revisao
     * @return
     */
    @RequestMapping("cadastrarRevisao.html")
    public RedirectView cadastrarRevisao(Revisao revisao) {
        revisoesRepository.save(revisao);
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
        mv.addObject("revisao", revisoesRepository.getOne(revisao.getId()));
        mv.addObject("avaliador", avaliadoresRepository.findAll());
        mv.addObject("trabalho", trabalhosRepository.findAll());
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
        mv.addObject("trabalho", revisoesRepository.getOne(revisao.getTrabalho().getId()));
        mv.setViewName("trabalho/realizarRevisao");
        return mv;
    }

    /**
     *
     * @param revisao
     * @return
     */
    @RequestMapping("listarRevisoesAvaliador.html")
    public ModelAndView listarRevisoesAvaliador(Revisao revisao) {
        ModelAndView mv = new ModelAndView();
        if (revisao.getStatus() == "Avaliado") {
            mv.addObject("revisao", revisoesRepository.getOne(revisao.getId()));
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
        Revisao aux = revisoesRepository.getOne(revisao.getId());
        aux.setStatus(revisao.getStatus());
        revisoesRepository.save(aux);
        mv.setViewName("trabalho/alterarStatusRevisoesAvaliador");
        return mv;
    }

}