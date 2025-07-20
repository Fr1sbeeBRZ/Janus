package fr1sbee.dev.janus.client.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
    private static final String HOME_FOLDER = "/home/";

    public HomeController() {}

    @GetMapping("/home")
    public String index(){
        return HOME_FOLDER + "index";
    }
}
