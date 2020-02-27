package com.springboard.blog.web;

import com.springboard.blog.EmailVO;
import com.springboard.blog.PostVO;
import com.springboard.blog.service.PostService;
import com.springboard.user.UserVO;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class EmailController {

    /** Email Service */
    @Autowired
    private JavaMailSenderImpl mailSender;

    /**
     * Go to Write Email Page
     * @param model - Model. Send data and view
     * @param request - HttpServletRequest. Get other request
     * @param httpSession - HttpSession. Get session data
     * @param pageNm - String. Used page name value for ctnHead.jsp
     * @return String. Write Page
     * @exception Exception
     */
    @RequestMapping("/sendEmail.do")
    public String sendEmail(Model model, HttpServletRequest request, HttpSession httpSession) throws Exception {
        /** call session data for check sign-in */
        UserVO userVO = (UserVO) httpSession.getAttribute("signIn");

        if (userVO != null && userVO.getId().equals("admin")) {
            model.addAttribute("signIn", "true");
        } else if (userVO != null && userVO.getId().equals("test")) {
            model.addAttribute("signIn", "test");
        } else {
            model.addAttribute("signIn", "false");
        }
        model.addAttribute("pageNm", "sendEmail");
        model.addAttribute("bgNm", "contact");

        return "blog/sendEmail";
    }

    /**
     * Do Send Email
     * @param emailVO - EmailVO. email data
     * @param httpSession - HttpSession. Get session data
     * @param redirectAttributes - RedirectAttributes. For send Param to other Controller
     * @param pageNm - String. Used page name value for ctnHead.jsp
     * @return String. Main Page
     * @exception Exception
     */
    @RequestMapping("/doSendEmail.do")
    public String doSendEmail(EmailVO emailVO, HttpSession httpSession, RedirectAttributes redirectAttributes) throws Exception {
        /** call session data for check sign-in */
        UserVO userVO = (UserVO) httpSession.getAttribute("signIn");

        try {
            MimeMessage msg = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(msg, false, "UTF-8");
            helper.setTo(new InternetAddress("myhyem1018@gmail.com", "MyHyem"));
            helper.setSubject(emailVO.getEmailTitle());
            emailVO.setEmailContent("From : &lt;"+emailVO.getEmailFrom()+"&gt;<br><br>"+emailVO.getEmailContent());
            helper.setText(emailVO.getEmailContent(), true);

            mailSender.send(msg);

            redirectAttributes.addFlashAttribute("msg", "emailSuc");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "emailFail");
        }

        return "redirect:/";
    }
}
