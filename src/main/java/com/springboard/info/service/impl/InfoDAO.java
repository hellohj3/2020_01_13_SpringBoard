package com.springboard.info.service.impl;

import com.springboard.info.InfoVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Class for Infopage handling Process
 * @author MyHyem
 * @since 2020.02.15
 * @version 1.0
 * @see
 *
 * <pre>
 * << Modification Information >>
 *
 *   Modified date       Modifier              Modifications
 *  --------------      ----------       ---------------------------
 *  2020.02.15          MyHyem              Initial Creation
 *  </pre>
 */
@Repository("InfoDAO")
public class InfoDAO {

    @Autowired
    private SqlSessionTemplate mybatis;

    /**
     * Do Select info from database
     * @param pageNm - String. page name information
     * @return InfoVO. infoVO
     * @exception Exception
     */
    public InfoVO selectInfo(InfoVO infoVO) throws Exception {
        return mybatis.selectOne("InfoDAO.selectInfo", infoVO);
    }
}
