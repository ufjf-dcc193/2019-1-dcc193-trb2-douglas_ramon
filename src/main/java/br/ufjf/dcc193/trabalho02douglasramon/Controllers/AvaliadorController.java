package br.ufjf.dcc193.trabalho02douglasramon.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.ufjf.dcc193.trabalho02douglasramon.Models.AreaConhecimento;
import br.ufjf.dcc193.trabalho02douglasramon.Models.Avaliador;
import br.ufjf.dcc193.trabalho02douglasramon.Models.Trabalho;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.AreaConhecimentoRepository;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.AvaliadorRepository;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.TrabalhoRepository;

/**
 *
 * @authors douglas e ramon
 */
@Controller
public class AvaliadorController {
    @Autowired
    AvaliadorRepository avaliadoresRepository;
    @Autowired
    AreaConhecimentoRepository areaConhecimentosRepository;
    @Autowired
    TrabalhoRepository trabalhosRepository;

    /**
     *
     * @param model
     * @return
     */
    @GetMapping("/avaliador-listar.html")
    public ModelAndView avaliador() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "Avaliadores");
        mv.addObject("avaliadores", avaliadoresRepository.findAll());
        mv.setViewName("avaliador/listar");
        return mv;
    }

    /**
     *
     * @return
     */
    @GetMapping({"/avaliador-novo.html"})
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("avaliador/novo");
        mv.addObject("title", "Cadastrar Avaliador");
        return mv;
    }

    /**
     *
     * @param model
     * @return
     */
    public String formAvaliador(Model model) {
        model.addAttribute("areaConhecimento", areaConhecimentosRepository.findAll());
        return "avaliador/formAvaliador";
    }
    
    @PostMapping("/avaliador-excluir.html")
    public RedirectView remove(Avaliador avaliador) {
        avaliadoresRepository.deleteById(avaliador.getId());
        return new RedirectView("avaliador-listar.html");
    }

    /**
     *
     * @param avaliador
     * @return
     */
    @PostMapping({"avaliador-novo.html", "avaliador-editar.html"})
    public ModelAndView cadastrarAvaliador(@Valid Avaliador avaliador, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if(binding.hasErrors()){
            mv.setViewName("avaliador/editar");
            mv.addObject("avaliador", avaliador);
            mv.addObject("title", "Avaliador");
            return mv;
        } 
        avaliadoresRepository.save(avaliador);
        mv.setViewName("redirect:avaliador-listar.html");
        return mv;
    }

    /**
     *
     * @param avaliador
     * @return
     */
    @GetMapping("/avaliador-editar.html")
    public ModelAndView editarAvaliador(Avaliador avaliador) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("avaliador", avaliadoresRepository.getOne(avaliador.getId()));
        mv.setViewName("avaliador/editar");
        return mv;
    }

    @PostMapping("/login.html")
    public ModelAndView login(Avaliador av) {
        ModelAndView mv = new ModelAndView();
        List<Avaliador> avaliadores = avaliadoresRepository.findAll();
        mv.setViewName("redirect:index.html");
        for (Avaliador avaliador : avaliadores) {
            if(avaliador.getCodigo().equals(av.getCodigo()) && avaliador.getEmail().equals(av.getEmail())){
                mv.addObject("avaliador", avaliador);
                mv.setViewName("redirect:avaliador-home.html");
                return mv;
            }
        }
        return mv;
    }

    /**
     *
     * @param avaliador
     * @return true ou false
     */
    public Boolean identificacaoSistema(Avaliador avaliador) {
        Avaliador aux = avaliadoresRepository.getOne(avaliador.getId());
        if (aux != null) {
            if (aux.getEmail() == avaliador.getEmail() && aux.getCodigo() == avaliador.getCodigo()) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param avaliador
     * @return List<AreaConhecimento> ou null
     */
    public List<AreaConhecimento> getListaAreaConhecimentoAvaliador(Avaliador avaliador) {
        Avaliador aux = avaliadoresRepository.getOne(avaliador.getId());
        if (aux != null) {
            return aux.getAreaConhecimento();
        }
        return null;
    }

    /**
     *
     * @param avaliador
     * @return List<Trabalho> ou null
     */
    public List<Trabalho> getListaTrabalhosAreaConhecimentoAvaliador(Avaliador avaliador) {
        List<Trabalho> listaTrabalhos = new ArrayList<Trabalho>();
        Avaliador aux = avaliadoresRepository.getOne(avaliador.getId());
        Trabalho trabalho;

        if (aux != null) {
            for (int i = 0; i < aux.getAreaConhecimento().size(); i++) {
                trabalho = trabalhosRepository.getOne(avaliador.getAreaConhecimento().get(i).getId());
                listaTrabalhos.add(trabalho);
            }
            return listaTrabalhos;
        }

        return null;
    }

}