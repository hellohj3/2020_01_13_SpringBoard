package com.springboard.blog.web;

import com.springboard.blog.PostVO;
import com.springboard.blog.ReplyVO;
import com.springboard.blog.service.PostService;
import com.springboard.blog.service.ReplyService;
import com.springboard.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ReplyController {

    /** Post Service */
    @Autowired
    private PostService postService;

    /** Reply Service */
    @Autowired
    private ReplyService replyService;

    /**
     * Do Write Reply
     * @param replyVO - ReplyVO. reply data
     * @return String. Result message
     * @exception Exception
     */
    @RequestMapping("/doWriteReply.do")
    public String doWriteReply(ReplyVO replyVO) throws Exception {
        /** remove '<script>','</script>' */
        replyVO.setContent(replyVO.getContent().replace("&amp;lt;script&amp;gt;", ""));
        replyVO.setContent(replyVO.getContent().replace("&amp;lt;/script&amp;gt;", ""));
        replyService.insert(replyVO);

        return "success";
    }

    /**
     * Do Get Reply List
     * @param replyVO - ReplyVO. reply data
     * @return String. Result message
     * @exception Exception
     */
    @RequestMapping("/doListReply.do")
    public ResponseEntity<List<ReplyVO>> doListReply(ReplyVO replyVO) throws Exception {
        ResponseEntity<List<ReplyVO>> replyList = null;

        /** paging */
        if (replyVO.getPageNo() <=  0 ) {
            replyVO.setPageNo(1);
        }
        replyVO.setPageNo((replyVO.getPageNo()-1)*replyVO.getLimit());

        try {
            replyList = new ResponseEntity<>(replyService.replyList(replyVO), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            replyList = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return replyList;
    }

    /**
     * Do Delete Reply
     * @param replyVO - ReplyVO. reply data
     * @param httpSession - HttpSession. Get session data
     * @return String. Result message
     * @exception Exception
     */
    @RequestMapping("/doDeleteReply.do")
    public String doDeleteReply(ReplyVO replyVO, HttpSession httpSession) throws Exception {
        /** call session data for check sign-in */
        UserVO userVO = (UserVO) httpSession.getAttribute("signIn");

        if (userVO != null) {
            replyService.delete(replyVO);

            return "success";
        } else {
            return "fail";
        }
    }
}
