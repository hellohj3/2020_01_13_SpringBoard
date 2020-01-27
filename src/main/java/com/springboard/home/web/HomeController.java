package com.springboard.home.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    /**
     * Go to Main Page
     * @return Main Page
     * @exception Exception
     */
    @RequestMapping("/")
    public String home(Model model) throws Exception {
        model.addAttribute("pageNm", "home");

        return "home";
    }
}
