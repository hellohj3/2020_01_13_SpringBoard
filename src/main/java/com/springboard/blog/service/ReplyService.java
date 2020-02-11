package com.springboard.blog.service;

import com.springboard.blog.PostVO;
import com.springboard.blog.ReplyVO;

import java.util.List;

/**
 * Interface to Reply handling Process
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

public interface ReplyService {

    /**
     * Do Insert reply in database
     * @param replyVO - ReplyVO. reply data
     * @return void
     * @exception Exception
     */
    void insert(ReplyVO replyVO) throws Exception;

    /**
     * Do Select reply list from database
     * @return List. ReplyVO list
     * @exception Exception
     */
    List<ReplyVO> replyList(ReplyVO replyVO) throws Exception;

    /**
     * Do Delete reply from database
     * @param replyVO - ReplyVO. reply data
     * @return void
     * @exception Exception
     */
    void delete(ReplyVO replyVO) throws Exception;
}
