package com.springboard.user.service.impl;

import com.springboard.user.UserVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Class for User handling Process
 * @author MyHyem
 * @since 2020.01.27
 * @version 1.0
 * @see
 *
 * <pre>
 * << Modification Information >>
 *
 *   Modified date       Modifier              Modifications
 *  --------------      ----------       ---------------------------
 *  2020.01.27          MyHyem              Initial Creation
 *  </pre>
 */
@Repository("UserDAO")
public class UserDAO {

    @Autowired
    private SqlSessionTemplate mybatis;

    /**
     * Process SignIn
     * @param userVO UserVO
     * @return UserVO
     * @exception Exception
     */
    public UserVO doSignIn(UserVO userVO) throws Exception {
        return (UserVO)mybatis.selectOne("UserDAO.doSignIn", userVO);
    }

    /**
     * Process Insert
     * @param userVO UserVO
     * @return UserVO
     * @exception Exception
     */
    public void doInsert(UserVO userVO) throws Exception {
        mybatis.insert("UserDAO.doInsert", userVO);
    }
}
