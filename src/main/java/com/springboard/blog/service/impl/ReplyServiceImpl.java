package com.springboard.blog.service.impl;

import com.springboard.blog.PostVO;
import com.springboard.blog.ReplyVO;
import com.springboard.blog.service.ReplyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Class for Reply handling Process
 * @author MyHyem
 * @since 2020.02.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << Modification Information >>
 *
 *   Modified date       Modifier              Modifications
 *  --------------      ----------       ---------------------------
 *  2020.02.10          MyHyem              Initial Creation
 *  </pre>
 */
@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

    @Resource(name = "PostDAO")
    private PostDAO postDAO;

    @Resource(name = "ReplyDAO")
    private ReplyDAO replyDAO;

    /**
     * Do Insert reply in database
     * @param replyVO - ReplyVO. reply data
     * @return void
     * @exception Exception
     */
    @Transactional
    public void insert(ReplyVO replyVO) throws Exception {
        replyDAO.insertReply(replyVO);
        postDAO.updateReplyCnt(replyVO.getTargetIdx(), 1);
    }

    /**
     * Do Select reply list from database
     * @return List. ReplyVO list
     * @exception Exception
     */
    public List<ReplyVO> replyList(ReplyVO replyVO) throws Exception {
        return replyDAO.selectList(replyVO);
    }

    /**
     * Do Delete reply from database
     * @param replyVO - ReplyVO. reply data
     * @return void
     * @exception Exception
     */
    @Transactional
    public void delete(ReplyVO replyVO) throws Exception {
        replyDAO.deleteReply(replyVO);
        postDAO.updateReplyCnt(replyVO.getTargetIdx(), -1);
    }
}
