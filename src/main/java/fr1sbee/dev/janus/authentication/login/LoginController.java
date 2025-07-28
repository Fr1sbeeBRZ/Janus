package fr1sbee.dev.janus.authentication.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
    private final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    public LoginController() {
    }


    @GetMapping("/login")
    public String index() {
        return "/login/index";
    }
}
