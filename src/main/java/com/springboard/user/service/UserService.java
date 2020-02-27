package com.springboard.user.service;

import com.springboard.user.UserVO;

/**
 * Interface to User handling Process
 * @author MyHyem
 * @since 2020.01.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << Modification Information >>
 *
 *   Modified date       Modifier              Modifications
 *  --------------      ----------       ---------------------------
 *  2020.01.20          MyHyem              Initial Creation
 *  </pre>
 */

public interface UserService {

    /**
     * Do SignIn
     * @param userVO - UserVO. userVO data
     * @return UserVO
     * @exception Exception
     */
    UserVO doSignIn(UserVO userVO) throws Exception;

    /**
     * Do Insert
     * @param userVO - UserVO. userVO data
     * @return UserVO
     * @exception Exception
     */
    void doInsert(UserVO userVO) throws Exception;
}
