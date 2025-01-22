package app.web;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class IndexController {
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
    public String getHomePage(){

        return "home";
    }
}
