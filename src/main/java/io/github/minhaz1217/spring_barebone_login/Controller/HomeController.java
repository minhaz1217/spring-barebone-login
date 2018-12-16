package io.github.minhaz1217.spring_barebone_login.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/" )
    public String getRoot(){
        return "HELLO";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/login2")
    public String getLogin2(){
        return "Login";
    }
}
