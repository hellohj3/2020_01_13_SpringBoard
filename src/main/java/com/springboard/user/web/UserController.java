package com.springboard.user.web;

import com.springboard.user.UserVO;
import com.springboard.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {

    /** SignIn Service */
    @Autowired
    private UserService userService;

    /** Spring-security */
    @Inject
    PasswordEncoder passwordEncoder;

    /**
     * Go to SignIn Page
     * @param pageNm - String. Used page name value for ctnHead.jsp
     * @param msg - String. Used call alert message for foot.jsp
     * @param request - HttpServletRequest. For session handling
     * @return String. SignIn Page address
     * @exception Exception
     */
    @RequestMapping("/signIn.do")
    public String signIn(ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        /** call session data for check sign-in */
        HttpSession session = request.getSession();
        UserVO userVO = (UserVO)session.getAttribute("signIn");

        /** receive message date from other controller. msg */
        String msg = "";
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            msg = (String) flashMap.get("msg");
        }

        model.addAttribute("signIn", (userVO != null) ? "true" : "false");

        /** branch from sign-in data */
        if (userVO != null) {
            model.addAttribute("pageNm", "home");
            redirectAttributes.addFlashAttribute("msg", msg);

            return "redirect:/";
        } else {
            model.addAttribute("pageNm", "signIn");
            model.addAttribute("msg", msg);

            return "blog/signIn";
        }
    }

    /**
     * Do SignIn
     * @param userVO - UserVO. Get ID, PASSWORD data
     * @param resultVO - UserVO. Do sign-in get user data
     * @param request - HttpServletRequest. For session handling
     * @param redirectAttributes - RedirectAttributes. For send Param to other Controller
     * @return String. Redirect target controller mapping name.
     * @exception Exception
     */
    @RequestMapping("/doSignIn.do")
    public String doSignIn(UserVO userVO, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        UserVO resultVO = userService.doSignIn(userVO);
        HttpSession session = request.getSession();

        if (passwordEncoder.matches(userVO.getPassword(), resultVO.getPassword())) {
            session.setAttribute("signIn", resultVO);

            redirectAttributes.addFlashAttribute("msg", "signSuc");

            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("msg", "signFail");

            return "redirect:/signIn.do";
        }
    }

    /**
     * Do SignOut
     * @param resultVO - UserVO. Get session data
     * @param redirectAttributes - RedirectAttributes. For send Param to other Controller
     * @return String. Redirect target controller mapping name.
     * @exception Exception
     */
    @RequestMapping("/doSignOut.do")
    public String doSignOut(RedirectAttributes redirectAttributes, HttpSession httpSession) throws Exception {
        UserVO resultVO = (UserVO) httpSession.getAttribute("signIn");

        if (resultVO != null) {
            httpSession.removeAttribute("signIn");
            httpSession.invalidate();
            redirectAttributes.addFlashAttribute("msg", "signOut");
        }

        return "redirect:/";
    }
}
