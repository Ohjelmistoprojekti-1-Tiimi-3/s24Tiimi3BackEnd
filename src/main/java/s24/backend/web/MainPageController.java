package s24.backend.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/main")
    public String showMainPage(){
        return "mainpage";
    }
}
