package com.springboard.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class HomeController {

    /**
     * Go to Main Page
     * @return Main Page
     * @param pageNm - Used page name value for ctnHead.jsp. String
     * @exception Exception
     */
    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request) throws Exception {
        String msg = "";
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            msg = (String) flashMap.get("msg");
        }

        model.addAttribute("pageNm", "home");
        model.addAttribute("msg", msg);

        return "blog/home";
    }
}
