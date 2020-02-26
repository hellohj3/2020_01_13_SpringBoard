package com.springboard.info.service.impl;

import com.springboard.info.InfoVO;
import com.springboard.info.service.InfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
@Service("infoService")
public class InfoServiceImpl implements InfoService {

    @Resource(name = "InfoDAO")
    private InfoDAO infoDAO;

    /**
     * Do Select info from database
     * @param pageNm - String. page name information
     * @return InfoVO. infoVO
     * @exception Exception
     */
    public InfoVO read(InfoVO infoVO) throws Exception {
        return infoDAO.selectInfo(infoVO);
    }
}
