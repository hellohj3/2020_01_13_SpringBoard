package com.springboard.user.web;

import com.springboard.user.UserVO;
import com.springboard.user.service.SignInService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class SingInController {

    /** SignIn Service */
    private SignInService signInService;

    /**
     * Go to SignIn Page
     * @param userVO - Include URL data. UserVO
     * @return SignIn Page
     * @exception Exception
     */
    @RequestMapping("/signInView.do")
    public String signInView(ModelMap model) throws Exception {
        model.addAttribute("pageNm", "signIn");

        return "";
    }

    /**
     * Do SignIn
     * @param userVO - Include ID, PASSWORD data. UserVO
     * @param request - For session hanfling. HttpServletRequest
     * @return resultVO - Result of SignIn (session data)
     * @exception Exception
     */
    @RequestMapping("/doSignIn.do")
    public String doSignIn(ModelMap model) throws Exception {
        UserVO userVO = new UserVO();


        model.addAttribute("pageNm", "home");

        return "";
    }

    @RequestMapping("/signIn.do")
    public String signIn(ModelMap model) {
        model.addAttribute("pageNm", "home");

        return "redirect:/";
    }
}
