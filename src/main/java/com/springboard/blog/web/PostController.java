package com.springboard.blog.web;

import com.springboard.blog.PostVO;
import com.springboard.blog.service.PostService;
import com.springboard.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class PostController {

    /** Post Service */
    @Autowired
    private PostService postService;

    /**
     * Go to Main Page
     * @param model - Model. Send data and view
     * @param request - HttpServletRequest. Get other request
     * @param httpSession - HttpSession. Get session data
     * @param pageNm|bgNm - String|String. Used value for ctnHead.jsp
     * @return String. Main Page
     * @exception Exception
     */
    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request, HttpSession httpSession) throws Exception {
        /** call session data for check sign-in */
        UserVO userVO = (UserVO) httpSession.getAttribute("signIn");

        /** receive message date from other controller. msg*/
        String msg = "";
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            msg = (String) flashMap.get("msg");
        }

        /** get post list */

        model.addAttribute("signIn", (userVO != null) ? "true" : "false");
        model.addAttribute("pageNm", "home");
        model.addAttribute("bgNm", "home");
        model.addAttribute("msg", msg);

        return "blog/home";
    }

    /**
     * Go to Write Post Page
     * @param model - Model. Send data and view
     * @param request - HttpServletRequest. Get other request
     * @param httpSession - HttpSession. Get session data
     * @param redirectAttributes - RedirectAttributes. For send Param to other Controller
     * @param pageNm - String. Used page name value for ctnHead.jsp
     * @return String. Main Page
     * @exception Exception
     */
    @RequestMapping("/writePost.do")
    public String writePost(Model model, HttpServletRequest request, HttpSession httpSession, RedirectAttributes redirectAttributes) throws Exception {
        /** call session data for check sign-in */
        UserVO userVO = (UserVO) httpSession.getAttribute("signIn");

        if (userVO != null) {
            model.addAttribute("signIn", "true");
            model.addAttribute("pageNm", "writePost");
            model.addAttribute("bgNm", "post");

            return "blog/writePost";
        } else {
            redirectAttributes.addFlashAttribute("msg", "pleaseSignIn");

            return "redirect:/";
        }
    }

    /**
     * Do Write Post
     * @param postVO - PostVO. PostVO. post data
     * @param httpSession - HttpSession. Get session data
     * @param redirectAttributes - RedirectAttributes. For send Param to other Controller
     * @param pageNm - String. Used page name value for ctnHead.jsp
     * @return String. Main Page
     * @exception Exception
     */
    @RequestMapping("/doWritePost.do")
    public String doWritePost(PostVO postVO, HttpSession httpSession, RedirectAttributes redirectAttributes) throws Exception {
        /** call session data for check sign-in */
        UserVO userVO = (UserVO) httpSession.getAttribute("signIn");

        if (userVO != null) {
            postVO.setWriter(userVO.getName());
            postService.insert(postVO);
        } else {
            redirectAttributes.addFlashAttribute("msg", "pleaseSignIn");
        }

        return "redirect:/";
    }
}
