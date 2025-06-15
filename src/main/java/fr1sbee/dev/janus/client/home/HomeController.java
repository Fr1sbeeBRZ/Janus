package fr1sbee.dev.janus.client.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/home")
@Controller
public class HomeController {
    private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    private final String HOME_FOLDER = "home/";

    public HomeController() {}

    @GetMapping("/")
    public String index(){
        return HOME_FOLDER + "index";
    }


}
