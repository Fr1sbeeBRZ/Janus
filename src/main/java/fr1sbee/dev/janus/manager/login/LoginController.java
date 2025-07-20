package fr1sbee.dev.janus.manager.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {
    private final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    public LoginController() {
    }


    @GetMapping("/login")
    public String index() {
        return "/login/index";
    }

    @PostMapping("/login")
    public String login() {

        return "redirect:/home";
    }
}
