package br.ufjf.dcc193.trabalho02douglasramon.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.ufjf.dcc193.trabalho02douglasramon.Models.AreaConhecimento;
import br.ufjf.dcc193.trabalho02douglasramon.Models.Avaliador;
import br.ufjf.dcc193.trabalho02douglasramon.Models.Revisao;
import br.ufjf.dcc193.trabalho02douglasramon.Models.Trabalho;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.AreaConhecimentoRepository;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.AvaliadorRepository;
import br.ufjf.dcc193.trabalho02douglasramon.Persistence.RevisaoRepository;
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
    @Autowired
    RevisaoRepository revisoesRepository;

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
    @GetMapping({ "/avaliador-novo.html" })
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("avaliador/novo");
        mv.addObject("title", "Cadastrar Avaliador");
        mv.addObject("areas", areaConhecimentosRepository.findAll());
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
    @PostMapping({ "avaliador-novo.html", "avaliador-editar.html" })
    public ModelAndView cadastrarAvaliador(@Valid Avaliador avaliador, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
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
    public ModelAndView login(Avaliador av, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        List<Avaliador> avaliadores = avaliadoresRepository.findAll();
        mv.setViewName("redirect:index.html");
        for (Avaliador avaliador : avaliadores) {
            if (avaliador.getCodigo().equals(av.getCodigo()) && avaliador.getEmail().equals(av.getEmail())) {
                mv.addObject("avaliador", avaliador);
                mv.setViewName("redirect:meus-dados.html");
                session.setAttribute("user", avaliador);
                return mv;
            }
        }
        return mv;
    }

    @GetMapping("/meus-dados.html")
    public ModelAndView home(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        if (session.getAttribute("user") != null) {
            Avaliador av = (Avaliador) session.getAttribute("user");
            mv.addObject("avaliador", av);
            mv.addObject("title", "Meus dados");
            mv.setViewName("avaliador/restrito/meus-dados");
            return mv;
        }
        mv.setViewName("redirect:index.html");
        return mv;
    }

    @GetMapping("/meus-trabalhos.html")
    public ModelAndView meusTrabalhos(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:index.html");
        if (session.getAttribute("user") != null) {
            Avaliador av = (Avaliador) session.getAttribute("user");
            av = avaliadoresRepository.getOne(av.getId());
            List<Trabalho> trabalhos = new ArrayList<>();
            for (AreaConhecimento area : av.getAreaConhecimento()) {
                List<Trabalho> t = trabalhosRepository.listaTrabalhoByArea(area.getId());
                trabalhos.addAll(t);
                List<Trabalho> tNaoAvaliado = trabalhosRepository.listaTrabalhosNaoAvaliados(area.getId());
                trabalhos.removeAll(tNaoAvaliado);
            }
            mv.addObject("title", "Meus trabalhos pendentes");
            mv.addObject("trabalhos", trabalhos);
            mv.setViewName("avaliador/restrito/meus-trabalhos");
            return mv;
        }
        return mv;
    }

    @RequestMapping("/revisar.html")
    public ModelAndView revisar(Trabalho t, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        if (session.getAttribute("user") != null) {
            Trabalho trabalho = trabalhosRepository.findById(t.getId()).get();
            Avaliador av = (Avaliador) session.getAttribute("user");
            Revisao r = revisoesRepository.getRevisaoTrabalhoAvaliador(trabalho.getId(), av.getId());
            if (r == null) {
                r = new Revisao();
                r.setNota(0);
                r.setDescricao("");
            }
            mv.addObject("revisao", r);
            mv.setViewName("redirect:index.html");
            mv.addObject("trabalho", trabalho);
            mv.addObject("idTrabalho", trabalho.getId());
        }

        mv.setViewName("avaliador/restrito/revisar");
        return mv;
    }

    @PostMapping(value = { "/revisar" }, params = "revisarDepois")
    public ModelAndView revisarDepois(@Valid Revisao revisao, BindingResult binding,
            @RequestParam(value = "id", required = true) Long id, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        if (session.getAttribute("user") != null) {
            if (binding.hasErrors()) {
                mv.setViewName("avaliador/restrito/revisar");
                mv.addObject("idTrabalho", id);
                mv.addObject("trabalho", trabalhosRepository.getOne(id));
                mv.addObject("title", "Revisar");
                return mv;
            }
            Avaliador avaliador = (Avaliador) session.getAttribute("user");
            Trabalho trabalho = trabalhosRepository.findById(id).get();
            Revisao r = revisoesRepository.getRevisaoTrabalhoAvaliador(trabalho.getId(), avaliador.getId());
            if(r != null) {
                revisao.setId(r.getId());
            }
            revisao.setStatus("A fazer");
            revisao.setTrabalho(trabalho);
            revisao.setAvaliador(avaliador);
            revisoesRepository.save(revisao);
        }
        mv.setViewName("redirect:meus-trabalhos.html");
        return mv;
    }

    @RequestMapping(value = { "/revisar" }, params = "revisarAgora", method = RequestMethod.POST)
    public ModelAndView revisarAgora(@Valid Revisao revisao, BindingResult binding,
            @RequestParam(value = "id", required = true) Long id, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        if (session.getAttribute("user") != null) {
            if (binding.hasErrors()) {
                mv.setViewName("avaliador/restrito/revisar");
                mv.addObject("idTrabalho", id);
                mv.addObject("trabalho", trabalhosRepository.getOne(id));
                mv.addObject("title", "Revisar");
                return mv;
            }
            Avaliador avaliador = (Avaliador) session.getAttribute("user");
            Trabalho trabalho = trabalhosRepository.findById(id).get();
            Revisao r = revisoesRepository.getRevisaoTrabalhoAvaliador(trabalho.getId(), avaliador.getId());
            if(r != null) {
                revisao.setId(r.getId());
            }
            revisao.setStatus("Avaliado");
            revisao.setTrabalho(trabalho);
            revisao.setAvaliador(avaliador);
            revisoesRepository.save(revisao);
        }
        mv.setViewName("redirect:meus-trabalhos.html");
        return mv;
    }

    @RequestMapping(value = { "/revisar" }, params = "pular", method = RequestMethod.POST)
    public ModelAndView pular(@RequestParam(value = "id", required = true) Long id, Revisao revisao,
            HttpSession session) {
        ModelAndView mv = new ModelAndView();
        if (session.getAttribute("user") != null) {
            revisao.setDescricao("");
            revisao.setNota(-1);
            revisao.setStatus("Impedido");
            revisoesRepository.save(revisao);
        }
        mv.setViewName("redirect:meus-trabalhos.html");
        return mv;
    }

    @GetMapping("/minhas-areas.html")
    public ModelAndView minhasAreas(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:index.html");
        if (session.getAttribute("user") != null) {
            Avaliador av = (Avaliador) session.getAttribute("user");
            av = avaliadoresRepository.getOne(av.getId());
            mv.addObject("areas", av.getAreaConhecimento());
            mv.addObject("title", "Minhas Áreas de Conhecimento");
            mv.setViewName("avaliador/restrito/minhas-areas");
            return mv;
        }
        return mv;
    }

    @GetMapping("/minhas-revisoes.html")
    public ModelAndView minhasRevisoes(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:index.html");
        if (session.getAttribute("user") != null) {
            Avaliador av = (Avaliador) session.getAttribute("user");
            mv.addObject("revisoes", revisoesRepository.getTodasRevisoesAvaliador(av.getId()));
            mv.setViewName("avaliador/restrito/minhas-revisoes");
            return mv;
        }
        return mv;
    }

    @GetMapping("/adicionar-area.html")
    public ModelAndView novaArea(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:index.html");
        if (session.getAttribute("user") != null) {
            mv.addObject("areas", areaConhecimentosRepository.findAll());
            mv.setViewName("avaliador/restrito/adicionar-area");
            mv.addObject("title", "Adicionar Área");
            return mv;
        }
        return mv;
    }

    @PostMapping("/adicionar-area.html")
    public ModelAndView salvarArea(AreaConhecimento ac, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:index.html");
        if (session.getAttribute("user") != null) {
            Avaliador av = (Avaliador) session.getAttribute("user");
            AreaConhecimento area = areaConhecimentosRepository.getOne(ac.getId());
            for (AreaConhecimento areaC : av.getAreaConhecimento()) {
                if (areaC.getId() == area.getId()) {
                    mv.addObject("title", "Adicionar Área");
                    mv.addObject("areas", areaConhecimentosRepository.findAll());
                    mv.addObject("error", "Você já possui esta Área de Conhecimento!");
                    mv.setViewName("avaliador/restrito/adicionar-area");
                    return mv;
                }
            }
            avaliadoresRepository.save(av);
            av.getAreaConhecimento().add(area);
            avaliadoresRepository.save(av);
            mv.setViewName("redirect:minhas-areas.html");
            return mv;
        }
        return mv;
    }

    @PostMapping("/remover-area-avaliador.html")
    public ModelAndView removerArea(AreaConhecimento ac, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:index.html");
        if (session.getAttribute("user") != null) {
            AreaConhecimento area = areaConhecimentosRepository.getOne(ac.getId());
            Avaliador av = (Avaliador) session.getAttribute("user");
            av = avaliadoresRepository.getOne(av.getId());
            av.getAreaConhecimento().remove(area);
            avaliadoresRepository.save(av);
            mv.setViewName("redirect:minhas-areas.html");
            return mv;
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