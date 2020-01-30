package com.springboard.user.web;

import com.springboard.user.UserVO;
import com.springboard.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class SignInController {

    /** SignIn Service */
    @Autowired
    private UserService userService;

    /** Spring-security */
    @Inject
    PasswordEncoder passwordEncoder;

    /**
     * Go to SignIn Page
     * @param pageNm - Used page name value for ctnHead.jsp. String
     * @return SignIn Page
     * @exception Exception
     */
    @RequestMapping("/signIn.do")
    public String signIn(ModelMap model, HttpServletRequest request) throws Exception {
        String msg = "";
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            msg = (String) flashMap.get("msg");
        }

        model.addAttribute("pageNm", "signIn");
        model.addAttribute("msg", msg);

        return "blog/signIn";
    }

    /**
     * Do SignIn
     * @param userVO - Include ID, PASSWORD data. UserVO
     * @param request - For session handling. HttpServletRequest
     * @return resultVO - Result of SignIn (session data)
     * @exception Exception
     */
    @RequestMapping("/doSignIn.do")
    public String doSignIn(UserVO userVO, RedirectAttributes redirectAttributes) throws Exception {
        UserVO resultVO = userService.doSignIn(userVO);

        if (passwordEncoder.matches(userVO.getPassword(), resultVO.getPassword())) {
            redirectAttributes.addFlashAttribute("msg", "signSuc");

            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("msg", "signFail");

            return "redirect:/signIn.do";
        }
    }
}
