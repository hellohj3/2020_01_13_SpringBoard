package com.springboard.info.service;

import com.springboard.info.InfoVO;

/**
 * Interface to Infopage handling Process
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

public interface InfoService {

    /**
     * Do Select info from database
     * @param pageNm - String. page name information
     * @return InfoVO. infoVO
     * @exception Exception
     */
    InfoVO read(InfoVO infoVO) throws Exception ;
}
