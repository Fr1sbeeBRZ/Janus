package fr1sbee.dev.janus.web.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
    private static final String HOME_FOLDER = "home/";

    public HomeController() {}

    @GetMapping("/home")
    public String index(Model model) {
        return "home/index";
    }
}
