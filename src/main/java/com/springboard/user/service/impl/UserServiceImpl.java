
package com.springboard.user.service.impl;

import com.springboard.user.UserVO;
import com.springboard.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Class to SignIn Process
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
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "UserDAO")
    private UserDAO userDAO;

    /**
     * Do SignIn
     * @param userVO UserVO
     * @return UserVO
     * @exception Exception
     */

    @Override
    public UserVO doSignIn(UserVO userVO) throws Exception {
        userVO = userDAO.doSignIn(userVO);
        return userVO;
    }
}
