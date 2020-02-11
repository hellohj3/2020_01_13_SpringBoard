package com.springboard.blog.service.impl;

import com.springboard.blog.ReplyVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
@Repository("ReplyDAO")
public class ReplyDAO {

    @Autowired
    private SqlSessionTemplate mybatis;

    /**
     * Do Insert reply in database
     * @param replyVO - ReplyVO. reply data
     * @return void
     * @exception Exception
     */
    public void insertReply(ReplyVO replyVO) throws Exception {
        mybatis.insert("ReplyDAO.insertReply", replyVO);
    }

    /**
     * Do Select reply list from database
     * @return List. ReplyVO list
     * @exception Exception
     */
    public List<ReplyVO> selectList(ReplyVO replyVO) throws Exception {
        return mybatis.selectList("ReplyDAO.selectList", replyVO);
    }

    /**
     * Do Delete reply from database
     * @param replyVO - ReplyVO. reply data
     * @return void
     * @exception Exception
     */
    public void deleteReply(ReplyVO replyVO) throws Exception {
        mybatis.delete("ReplyDAO.deleteReply", replyVO);
    }
}
