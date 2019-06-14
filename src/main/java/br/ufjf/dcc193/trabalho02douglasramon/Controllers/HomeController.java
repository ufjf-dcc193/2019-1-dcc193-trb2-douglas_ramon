package br.ufjf.dcc193.trabalho02douglasramon.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String index() {
        return "index";
    }
}