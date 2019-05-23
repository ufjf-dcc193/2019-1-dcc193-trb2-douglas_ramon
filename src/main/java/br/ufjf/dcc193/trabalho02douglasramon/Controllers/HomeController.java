package br.ufjf.dcc193.trabalho02douglasramon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * HomeController
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "<h1>Bem vindo!</h1>";
    }
}