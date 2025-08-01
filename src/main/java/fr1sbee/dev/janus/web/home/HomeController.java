package fr1sbee.dev.janus.web.home;

import fr1sbee.dev.janus.authentication.AuthenticationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
    private static final String HOME_FOLDER = "home/";

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private final AuthenticationFacade authenticationFacade;

    public HomeController(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("profile" , authenticationFacade.getCurrentProfile());
        return "home/index";
    }
}
