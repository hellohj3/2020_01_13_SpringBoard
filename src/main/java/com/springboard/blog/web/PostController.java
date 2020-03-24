package com.springboard.blog.web;

import com.springboard.blog.PostVO;
import com.springboard.blog.service.PostService;
import com.springboard.commons.pagination.Criteria;
import com.springboard.commons.pagination.PageMaker;
import com.springboard.user.UserVO;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
public class PostController {

    /** Post Service */
    @Autowired
    private PostService postService;

    /**
     * Go to Main Page
     * @param model - Model. Send data and view
     * @param postVO - PostVO. post data
     * @param request - HttpServletRequest. Get other request
     * @param httpSession - HttpSession. Get session data
     * @param pageNm|bgNm - String|String. Used value for ctnHead.jsp
     * @return String. Main Page
     * @exception Exception
     */
    @RequestMapping("/")
    public String home(Model model, PostVO postVO, HttpServletRequest request, HttpSession httpSession, HttpServletResponse response, Locale locale) throws Exception {
        /** call session data for check sign-in */
        UserVO userVO = (UserVO) httpSession.getAttribute("signIn");

        /** receive message date from other controller. msg*/
        String msg = "";
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            msg = (String) flashMap.get("msg");
        }

        /** paging */
        Criteria criteria = new Criteria();
        criteria.setPage(postVO.getPageNo());
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);
        pageMaker.setTotalCount(postService.postListCnt(criteria));

        /** get post list */
        List<PostVO> postList = postService.postList(criteria);

        /** convert html from content */
        for (PostVO getVO : postList) {
            getVO.setContent(StringEscapeUtils.unescapeHtml4(getVO.getContent()));
        }

        if (userVO != null && userVO.getId().equals("admin")) {
            model.addAttribute("signIn", "true");
        } else if (userVO != null && userVO.getId().equals("test")) {
            model.addAttribute("signIn", "test");
        } else {
            model.addAttribute("signIn", "false");
        }
        model.addAttribute("pageNm", "home");
        model.addAttribute("bgNm", "home");
        model.addAttribute("msg", msg);
        model.addAttribute("postList", postList);
        model.addAttribute("pageMaker", pageMaker);

        model.addAttribute("locale", locale.toLanguageTag());

        return "blog/home";
    }

    /**
     * Go to Write Post Page
     * @param model - Model. Send data and view
     * @param request - HttpServletRequest. Get other request
     * @param httpSession - HttpSession. Get session data
     * @param redirectAttributes - RedirectAttributes. For send Param to other Controller
     * @param pageNm - String. Used page name value for ctnHead.jsp
     * @return String. Write Page
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
     * @param postVO - PostVO. post data
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
            /** remove '<script>','</script>' */
            postVO.setContent(postVO.getContent().replace("&amp;lt;script&amp;gt;", ""));
            postVO.setContent(postVO.getContent().replace("&amp;lt;/script&amp;gt;", ""));
            postVO.setWriter(userVO.getName());
            postService.insert(postVO);

            redirectAttributes.addFlashAttribute("msg", "writeSuc");
        } else {
            redirectAttributes.addFlashAttribute("msg", "pleaseSignIn");
        }

        return "redirect:/";
    }

    /**
     * Go to Modify Post Page
     * @param model - Model. Send data and view
     * @param postVO - PostVO. post data
     * @param httpSession - HttpSession. Get session data
     * @param redirectAttributes - RedirectAttributes. For send Param to other Controller
     * @param pageNm - String. Used page name value for ctnHead.jsp
     * @return String. Modify Page
     * @exception Exception
     */
    @RequestMapping("/modifyPost.do")
    public String modifyPost(Model model, PostVO postVO, HttpSession httpSession, RedirectAttributes redirectAttributes) throws Exception {
        /** call session data for check sign-in */
        UserVO userVO = (UserVO) httpSession.getAttribute("signIn");

        if (userVO != null) {
            /** get post */
            PostVO resultVO = postService.read(postVO);
            /** convert html from content */
            resultVO.setContent(StringEscapeUtils.unescapeHtml4(resultVO.getContent()));

            model.addAttribute("signIn", userVO.getId().equals("admin") ? "true" : "test");
            model.addAttribute("pageNm", "modifyPost");
            model.addAttribute("bgNm", "post");
            model.addAttribute("post", resultVO);

            return "blog/writePost";
        } else {
            redirectAttributes.addFlashAttribute("msg", "pleaseSignIn");

            return "redirect:/";
        }
    }

    /**
     * Do Modify Post
     * @param postVO - PostVO. post data
     * @param httpSession - HttpSession. Get session data
     * @param redirectAttributes - RedirectAttributes. For send Param to other Controller
     * @param pageNm - String. Used page name value for ctnHead.jsp
     * @return String. Detail Post Page
     * @exception Exception
     */
    @RequestMapping("/doModifyPost.do")
    public String doModifyPost(PostVO postVO, HttpSession httpSession, RedirectAttributes redirectAttributes) throws Exception {
        /** call session data for check sign-in */
        UserVO userVO = (UserVO) httpSession.getAttribute("signIn");

        if (userVO != null) {
            Date time = new Date();
            SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");

            /** remove '<script>','</script>' */
            postVO.setContent(postVO.getContent().replace("&amp;lt;script&amp;gt;", ""));
            postVO.setContent(postVO.getContent().replace("&amp;lt;/script&amp;gt;", ""));
            postVO.setModifyDate(format.format(time));

            postService.update(postVO);
        } else {
            redirectAttributes.addFlashAttribute("msg", "pleaseSignIn");
        }

        return "redirect:/detailePost.do?idx="+postVO.getIdx();
    }

    /**
     * Go Detail Post Page
     * @param model - Model. Send data and view
     * @param postVO - PostVO. post data
     * @param httpSession - HttpSession. Get session data
     * @param pageNm - String. Used page name value for ctnHead.jsp
     * @return String. Detail Page
     * @exception Exception
     */
    @RequestMapping("/detailePost.do")
    public String detailePost(Model model, HttpSession httpSession, PostVO postVO) throws Exception {
        /** call session data for check sign-in */
        UserVO userVO = (UserVO) httpSession.getAttribute("signIn");

        /** get post */
        PostVO resultVO = postService.read(postVO);
        /** convert html from content */
        resultVO.setContent(StringEscapeUtils.unescapeHtml4(resultVO.getContent()));

        if (userVO != null && userVO.getId().equals("admin")) {
            model.addAttribute("signIn", "true");
        } else if (userVO != null && userVO.getId().equals("test")) {
            model.addAttribute("signIn", "test");
        } else {
            model.addAttribute("signIn", "false");
        }
        model.addAttribute("pageNm", "detailPost");
        model.addAttribute("bgNm", "post");
        model.addAttribute("post", resultVO);
        model.addAttribute("user", userVO);

        return "/blog/detailPost";
    }

    /**
     * Do Delete Post
     * @param postVO - PostVO. PostVO. post data
     * @param httpSession - HttpSession. Get session data
     * @param redirectAttributes - RedirectAttributes. For send Param to other Controller
     * @param pageNm - String. Used page name value for ctnHead.jsp
     * @return String. Main Page
     * @exception Exception
     */
    @RequestMapping("/doDeletePost.do")
    public String doDeletePost(PostVO postVO, HttpSession httpSession, RedirectAttributes redirectAttributes) throws Exception {
        /** call session data for check sign-in */
        UserVO userVO = (UserVO) httpSession.getAttribute("signIn");

        if (userVO != null) {
            postService.delete(postVO);
        } else {
            redirectAttributes.addFlashAttribute("msg", "pleaseSignIn");
        }

        return "redirect:/";
    }
}
