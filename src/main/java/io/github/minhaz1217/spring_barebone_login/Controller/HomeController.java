package io.github.minhaz1217.spring_barebone_login.Controller;


import io.github.minhaz1217.spring_barebone_login.Model.User;
import io.github.minhaz1217.spring_barebone_login.Repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private UserRepository userRepository;
    HomeController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/" )
    public String getRoot(){
        return "HELLO";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/login2")
    public String getLogin2(){
        return "Login";
    }
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/test/{user}/{pass}")
    public User fromUsername(@PathVariable String user, @PathVariable String pass){
        // to get values from this endpoint
        // Use urls like this :
        // http://localhost:28080/test/myUsername/myPassword

        return userRepository.findUserByUsername(user);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/test2")
    public User fromUsername2(@RequestParam(value = "user")String user, @RequestParam(value = "pass") String pass){
        // to get values from this endpoint use it like this:
        // http://localhost:28080/test2?user=myUsername&pass=myPassword
        return userRepository.findUserByUsernameAndPassword(user, pass);
    }


}
