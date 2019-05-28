package br.ufjf.dcc193.trabalho02douglasramon.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "<h1>Bem vindo!</h1>";
    }
}