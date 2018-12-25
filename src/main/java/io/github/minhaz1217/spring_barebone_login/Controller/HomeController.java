package io.github.minhaz1217.spring_barebone_login.Controller;


import io.github.minhaz1217.spring_barebone_login.Model.User;
import io.github.minhaz1217.spring_barebone_login.Repository.UserRepository;
import io.github.minhaz1217.spring_barebone_login.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private CustomUserDetailsService userService;

    private UserRepository userRepository;
    HomeController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    /*
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/" )
    public String getRoot(){
        return "HELLO";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/signin")
    public String getSignin(){
        return "Login";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/signin")
    public String postSignin(@RequestParam String user, @RequestParam String pass){
        System.out.println(user + " " + pass);
        return "THIS IS THE POST SIGNIN";
        //return userRepository.findUserByUsernameAndPassword(user, pass);
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

*/







    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/signup")
    public ModelAndView signup(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if(userExists != null){
            bindingResult
                    .rejectValue("email", "error.user","There is already a user register with the username provided" );
        }
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("signup");
        }else{
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");

        }
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dashboard")
    public ModelAndView dashboard(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome "+ user.getUsername());
        modelAndView.addObject("adminMessage", "Content available for users with admin role");
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/", "/home"} )
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }




}
