package com.springboard.blog.web;

import com.springboard.user.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class BlogController {

    /**
     * Go to Main Page
     * @param pageNm - String. Used page name value for ctnHead.jsp
     * @return String. Main Page
     * @exception Exception
     */
    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request) throws Exception {
        /** call session data for check sign-in */
        HttpSession session = request.getSession();
        UserVO userVO = (UserVO)session.getAttribute("signIn");

        /** receive message date from other controller. msg*/
        String msg = "";
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            msg = (String) flashMap.get("msg");
        }

        model.addAttribute("signIn", (userVO != null) ? "true" : "false");
        model.addAttribute("pageNm", "home");
        model.addAttribute("msg", msg);

        return "blog/home";
    }
}
