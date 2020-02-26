package com.springboard.blog.service.impl;

import com.springboard.blog.PostVO;
import com.springboard.blog.service.PostService;
import com.springboard.commons.pagination.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
@Service("postService")
public class PostServiceImpl implements PostService {

    @Resource(name = "PostDAO")
    private PostDAO postDAO;

    /**
     * Do Insert post in database
     * @param postVO - PostVO. post data
     * @return void
     * @exception Exception
     */
    public void insert(PostVO postVO) throws Exception {
        postDAO.insertPost(postVO);
        String[] files = postVO.getFiles();

        if (files == null) {
            return;
        }

        for (String fileName : files) {
            postDAO.addFile(fileName);
        }
    }

    /**
     * Do Select post from database
     * @param postVO - PostVO. post index number
     * @return PostVO. postVO
     * @exception Exception
     */
    @Transactional
    public PostVO read(PostVO postVO) throws Exception {
        postDAO.updateViewCnt(postVO);
        return postDAO.selectPost(postVO);
    }

    /**
     * Do Select post list from database
     * @param criteria - Criteria. pagination information
     * @return List. PostVO list
     * @exception Exception
     */
    public List<PostVO> postList(Criteria criteria) throws Exception {
        return postDAO.selectList(criteria);
    }

    /**
     * Do Select count post list from database
     * @param criteria - Criteria. pagination information
     * @return int. list count
     * @exception Exception
     */
    public int postListCnt(Criteria criteria) throws Exception {
        return postDAO.cntList(criteria);
    }

    /**
     * Do Update post from database
     * @param postVO - PostVO. postVO data
     * @return void
     * @exception Exception
     */
    public void update(PostVO postVO) throws Exception {
        postDAO.updatePost(postVO);
    }

    /**
     * Do Delete post from database
     * @param postVO - PostVO. postVO data
     * @return void
     * @exception Exception
     */
    public void delete(PostVO postVO) throws Exception {
        postDAO.deletePost(postVO);
    }

    /**
     * Do save file data
     * @param fullName - String. saved path + file name
     * @return void
     * @exception Exception
     */
    public void addFile(String fullName) throws Exception {
        postDAO.addFile(fullName);
    }
}
