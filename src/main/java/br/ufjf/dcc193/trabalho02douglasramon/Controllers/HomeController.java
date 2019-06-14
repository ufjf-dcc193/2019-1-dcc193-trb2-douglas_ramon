package br.ufjf.dcc193.trabalho02douglasramon.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @authors douglas e ramon
 */
@Controller
public class HomeController {

    /**
     *
     * @return
     */
    @RequestMapping({"/","/index.html"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("title", "Home");
        return mv;
    }
}