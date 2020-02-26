package com.springboard.info.web;

import com.springboard.info.InfoVO;
import com.springboard.info.service.InfoService;
import com.springboard.user.UserVO;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
public class InfoController {

    /** Info Service */
    @Autowired
    private InfoService infoService;

    /**
     * Go to Contact Me Page
     * @param model - Model. Send data and view
     * @param request - HttpServletRequest. Get other request
     * @param httpSession - HttpSession. Get session data
     * @param pageNm|bgNm - String|String. Used value for ctnHead.jsp
     * @return String. Contact Me Page
     * @exception Exception
     */
    @RequestMapping("/contactMe.do")
    public String contactMe(Model model, HttpServletRequest request, HttpSession httpSession) throws Exception {
        /** call session data for check sign-in */
        UserVO userVO = (UserVO) httpSession.getAttribute("signIn");

        model.addAttribute("signIn", (userVO != null) ? "true" : "false");
        model.addAttribute("pageNm", "contactMe");
        model.addAttribute("bgNm", "contact");

        return "info/contactMe";
    }

    /**
     * Go to About Me Page
     * @param model - Model. Send data and view
     * @param request - HttpServletRequest. Get other request
     * @param httpSession - HttpSession. Get session data
     * @param pageNm|bgNm - String|String. Used value for ctnHead.jsp
     * @return String. About Me Page
     * @exception Exception
     */
    @RequestMapping("/aboutMe.do")
    public String aboutMe(Model model, HttpServletRequest request, HttpSession httpSession, Locale locale) throws Exception {
        /** call session data for check sign-in */
        UserVO userVO = (UserVO) httpSession.getAttribute("signIn");

        InfoVO infoVO = new InfoVO();
        infoVO.setLang(locale.toString());

        infoVO = infoService.read(infoVO);
        infoVO.setContent(StringEscapeUtils.unescapeHtml4(infoVO.getContent()));

        model.addAttribute("signIn", (userVO != null) ? "true" : "false");
        model.addAttribute("pageNm", "aboutMe");
        model.addAttribute("bgNm", "about");
        model.addAttribute("info", infoVO);

        return "info/aboutMe";
    }
}
