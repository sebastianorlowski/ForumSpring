package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
