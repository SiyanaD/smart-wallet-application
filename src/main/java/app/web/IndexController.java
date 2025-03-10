package app.web;

import app.user.model.User;
import app.user.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;


@Controller
@RequestMapping
public class IndexController {
    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getIndexPage(){

        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(){

        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(){

        return "register";
    }

    @GetMapping("/home")
    public ModelAndView getHomePage(){
        //
       User user= userService.getById(UUID.fromString("ab0b5242-6b5c-4d76-8c9f-124be662b2d9"));


    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("home");
    modelAndView.addObject("user",user);
        return modelAndView;
    }
}
