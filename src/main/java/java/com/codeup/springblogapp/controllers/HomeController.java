package java.com.codeup.springblogapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String gotoLandingPage() {
        return "home";
    }

    @GetMapping("/home")
    public String goHome() {
        return "home";
    }
}
