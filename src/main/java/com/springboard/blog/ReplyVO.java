package com.springboard.blog;

import java.util.Date;

/**
 * Class for holding reply information
 * @author MyHyem
 * @since 2020.02.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << Modification Information >>
 *
 *   Modified date       Modifier              Modifications
 *  --------------      ----------       ---------------------------
 *  2020.02.10          MyHyem              Initial Creation
 *  </pre>
 */
public class ReplyVO {
    private int idx;
    private int targetIdx;
    private String content;
    private String writer;
    private Date regDate;
    private String modifyDate;
    private int pageNo;
    private int limit = 7;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getTargetIdx() {
        return targetIdx;
    }

    public void setTargetIdx(int targetIdx) {
        this.targetIdx = targetIdx;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "ReplyVO{" +
                "idx=" + idx +
                ", targetIdx=" + targetIdx +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", regDate=" + regDate +
                ", modifyDate='" + modifyDate + '\'' +
                ", pageNo=" + pageNo +
                ", limit=" + limit +
                '}';
    }
}
