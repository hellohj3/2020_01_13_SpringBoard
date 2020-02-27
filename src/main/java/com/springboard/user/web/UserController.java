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

    /** User Service */
    @Autowired
    private UserService userService;

    /** Spring-security */
    @Inject
    PasswordEncoder passwordEncoder;

    /**
     * Go to SignIn Page
     * @param model - Model. Send data and view
     * @param request - HttpServletRequest. Other request receive
     * @param redirectAttributes - RedirectAttributes. For send Param to other Controller
     * @param httpSession - HttpSession. Get session data
     * @param userVO - UserVO. Handling session data
     * @param pageNm|bgNm - String|String. Used value for ctnHead.jsp
     * @param msg - String. Used call alert message for foot.jsp
     * @param signIn - String. Used change message for head.jsp
     * @return String. SignIn Page address or main page
     * @exception Exception
     */
    @RequestMapping("/signIn.do")
    public String signIn(ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession httpSession) throws Exception {
        /** call session data for check sign-in */
        UserVO userVO = (UserVO) httpSession.getAttribute("signIn");

        /** receive message date from other controller. msg */
        String msg = "";
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            msg = (String) flashMap.get("msg");
        }

        /** branch from sign-in data */
        if (userVO != null) {
            model.addAttribute("pageNm", "home");
            model.addAttribute("bgNm", "home");
            redirectAttributes.addFlashAttribute("msg", msg);
            model.addAttribute("signIn", userVO.getId().equals("admin") ? "true" : "test");

            return "redirect:/";
        } else {
            model.addAttribute("pageNm", "signIn");
            model.addAttribute("bgNm", "home");
            model.addAttribute("msg", msg);
            model.addAttribute("signIn", "false");

            return "blog/signIn";
        }
    }

    /**
     * Do SignIn
     * @param userVO - UserVO. Get ID, PASSWORD data from view
     * @param request - HttpServletRequest. Other request receive
     * @param redirectAttributes - RedirectAttributes. For send Param to other Controller
     * @param resultVO - UserVO. Do sign-in get user data
     * @param httpsession - HttpSession. Add session data
     * @return String. Redirect target controller mapping name.
     * @exception Exception
     */
    @RequestMapping("/doSignIn.do")
    public String doSignIn(UserVO userVO, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        UserVO resultVO = userService.doSignIn(userVO);
        HttpSession httpSession = request.getSession();

        if (passwordEncoder.matches(userVO.getPassword(), resultVO.getPassword())) {
            httpSession.setAttribute("signIn", resultVO);

            redirectAttributes.addFlashAttribute("msg", "signSuc");

            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("msg", "signFail");

            return "redirect:/signIn.do";
        }
    }

    /**
     * Do SignOut
     * @param redirectAttributes - RedirectAttributes. For send Param to other Controller
     * @param httpSession - HttpSession. Get session data
     * @param userVO - UserVO. Handling session data
     * @return String. Redirect target controller mapping name.
     * @exception Exception
     */
    @RequestMapping("/doSignOut.do")
    public String doSignOut(RedirectAttributes redirectAttributes, HttpSession httpSession) throws Exception {
        UserVO userVO = (UserVO) httpSession.getAttribute("signIn");

        if (userVO != null) {
            httpSession.removeAttribute("signIn");
            httpSession.invalidate();
            redirectAttributes.addFlashAttribute("msg", "signOut");
        }

        return "redirect:/";
    }

    /**
     * Go to Insert Page
     * @param model - Model. Send data and view
     * @param request - HttpServletRequest. Other request receive
     * @param httpSession - HttpSession. Get session data
     * @param userVO - UserVO. Handling session data
     * @param pageNm|bgNm - String|String. Used value for ctnHead.jsp
     * @param signIn - String. Used change message for head.jsp
     * @return String. SignIn Page address or main page
     * @exception Exception
     */
    @RequestMapping("/insert.do")
    public String insert(ModelMap model, HttpServletRequest request, HttpSession httpSession) throws Exception {

        model.addAttribute("pageNm", "signIn");
        model.addAttribute("bgNm", "home");
        model.addAttribute("signIn", "false");

        return "blog/insertUser";
    }

    /**
     * Do Insert user data
     * @param httpSession - HttpSession. Get session data
     * @param userVO - UserVO. Handling session data
     * @return String. Redirect target controller mapping name.
     * @exception Exception
     */
    @RequestMapping("/doInsert.do")
    public String doInsert(HttpSession httpSession, UserVO userVO) throws Exception {
        userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));

        userService.doInsert(userVO);

        return "redirect:/";
    }
}
