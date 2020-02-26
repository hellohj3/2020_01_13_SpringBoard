package com.springboard.blog.service;

import com.springboard.blog.PostVO;
import com.springboard.commons.pagination.Criteria;

import java.util.List;

/**
 * Interface to Post handling Process
 * @author MyHyem
 * @since 2020.02.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << Modification Information >>
 *
 *   Modified date       Modifier              Modifications
 *  --------------      ----------       ---------------------------
 *  2020.02.01          MyHyem              Initial Creation
 *  </pre>
 */

public interface PostService {

    /**
     * Do Insert post in database
     * @param postVO - PostVO. post data
     * @return void
     * @exception Exception
     */
    void insert(PostVO postVO) throws Exception;

    /**
     * Do Select post from database
     * @param postVO - PostVO. post index number
     * @return PostVO. postVO
     * @exception Exception
     */
    PostVO read(PostVO postVO) throws Exception;

    /**
     * Do Select post list from database
     * @param criteria - Criteria. pagination information
     * @return List. PostVO list
     * @exception Exception
     */
    List<PostVO> postList(Criteria criteria) throws Exception;

    /**
     * Do Select count post list from database
     * @param criteria - Criteria. pagination information
     * @return int. list count
     * @exception Exception
     */
    int postListCnt(Criteria criteria) throws Exception;

    /**
     * Do Update post from database
     * @param postVO - PostVO. postVO data
     * @return void
     * @exception Exception
     */
    void update(PostVO postVO) throws Exception;

    /**
     * Do Delete post from database
     * @param postVO - PostVO. postVO data
     * @return void
     * @exception Exception
     */
    void delete(PostVO postVO) throws Exception;

    /**
     * Do save file data
     * @param fullName - String. saved path + file name
     * @return void
     * @exception Exception
     */
    void addFile(String fullName) throws Exception;
}
