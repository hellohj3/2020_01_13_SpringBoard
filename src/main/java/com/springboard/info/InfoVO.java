package com.springboard.info;

import java.util.Date;

/**
 * Class for holding infopage information
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
public class InfoVO {
    private int idx;
    private String pageNm;
    private String content;
    private String lang;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getPageNm() {
        return pageNm;
    }

    public void setPageNm(String pageNm) {
        this.pageNm = pageNm;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "InfoVO{" +
                "idx=" + idx +
                ", pageNm='" + pageNm + '\'' +
                ", content='" + content + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }
}
