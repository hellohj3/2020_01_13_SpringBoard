package com.springboard.blog.service.impl;

import com.springboard.blog.PostVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class for Post handling Process
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
@Repository("PostDAO")
public class PostDAO {

    @Autowired
    private SqlSessionTemplate mybatis;

    /**
     * Do Insert post in database
     * @param postVO - PostVO. post data
     * @return void
     * @exception Exception
     */
    public void insertPost(PostVO postVO) throws Exception {
        mybatis.insert("PostDAO.insertPost", postVO);
    }

    /**
     * Do Select post from database
     * @param postVO - PostVO. post index number
     * @return PostVO. postVO
     * @exception Exception
     */
    public PostVO selectPost(PostVO postVO) throws Exception {
        return (PostVO) mybatis.selectOne("PostDAO.selectPost", postVO);
    }

    /**
     * Do Select post list from database
     * @return List. PostVO list
     * @exception Exception
     */
    public List<PostVO> selectList() throws Exception {
        return mybatis.selectList("PostDAO.selectList");
    }

    /**
     * Do Update post from database
     * @param postVO - PostVO. postVO data
     * @return void
     * @exception Exception
     */
    public void updatePost(PostVO postVO) throws Exception {
        mybatis.update("PostDAO.updatePost", postVO);
    }

    /**
     * Do Delete post from database
     * @param postVO - PostVO. postVO data
     * @return void
     * @exception Exception
     */
    public void deletePost(PostVO postVO) throws Exception {
        mybatis.delete("PostDAO.deletePost", postVO);
    }
}
