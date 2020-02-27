package com.springboard.exp.web;

import com.springboard.user.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ExceptionController {

    /**
     * Intercept Error Data
     * @param model - Model. Send data and view
     * @param request - HttpServletRequest. Other request receive
     * @param httpSession - HttpSession. Get session data
     * @param error_code - PathVariable. Get error data
     * @param userVO - UserVO. Handling session data
     * @param signIn - String. Used change message for head.jsp
     * @param pageNm|bgNm - String|String. Used value for ctnHead.jsp
     * @param map - HashMap. Include Error data.
     * @return String. Error Page
     * @exception Exception
     */

    @RequestMapping("/exception{error_code}.do")
    public String error(Model model, HttpServletRequest request, HttpSession httpSession, @PathVariable String error_code) {
        String msg = (String) request.getAttribute("javax.servlet.error.message");

        Map map = new HashMap();
        map.put("STATUS_CODE", request.getAttribute("javax.servlet.error.status_code"));
        map.put("REQUEST_URI", request.getAttribute("javax.servlet.error.request_uri"));
        map.put("EXCEPTION_TYPE", request.getAttribute("javax.servlet.error.exception_type"));
        map.put("EXCEPTION", request.getAttribute("javax.servlet.error.exception"));
        map.put("SERVLET_NAME", request.getAttribute("javax.servlet.error.servlet_name"));

        try {
            int status_code = Integer.parseInt(error_code);
            switch (status_code) {
                case 400: msg = "invalidRqst"; break;
                case 403: msg = "notAuthDir"; break;
                case 404: msg = "notFound"; break;
                case 405: msg = "notAuth"; break;
                case 500: msg = "serverErr"; break;
                case 503: msg = "serviceUab"; break;
                default: msg = "unknownErr"; break;
            }
        } catch(Exception e) {
            msg = "etcErr";
        } finally {
            map.put("MESSAGE", msg);
        }

        /** call session data for check sign-in */
        UserVO userVO = (UserVO) httpSession.getAttribute("signIn");

        if (userVO != null && userVO.getId().equals("admin")) {
            model.addAttribute("signIn", "true");
        } else if (userVO != null && userVO.getId().equals("test")) {
            model.addAttribute("signIn", "test");
        } else {
            model.addAttribute("signIn", "false");
        }
        model.addAttribute("pageNm", "exception");
        model.addAttribute("bgNm", "home");
        model.addAttribute("exception", map);

        return "exception/exception";
    }
}
