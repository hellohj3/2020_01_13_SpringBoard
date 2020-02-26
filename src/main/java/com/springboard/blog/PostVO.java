package com.springboard.blog;

import java.util.Arrays;
import java.util.Date;

/**
 * Class for holding post information
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
public class PostVO {
    private int idx;
    private String title;
    private String subTitle;
    private String content;
    private String writer;
    private Date regDate;
    private String modifyDate;
    private int viewCnt;
    private int replyCnt;
    private int pageNo;
    private String[] files;
    private int fileCnt;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
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

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

    public int getReplyCnt() {
        return replyCnt;
    }

    public void setReplyCnt(int replyCnt) {
        this.replyCnt = replyCnt;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String[] getFiles() {
        return files;
    }

    public void setFiles(String[] files) {
        this.files = files;
        setFileCnt(files.length);
    }

    public int getFileCnt() {
        return fileCnt;
    }

    public void setFileCnt(int fileCnt) {
        this.fileCnt = fileCnt;
    }

    @Override
    public String toString() {
        return "PostVO{" +
                "idx=" + idx +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", regDate=" + regDate +
                ", modifyDate='" + modifyDate + '\'' +
                ", viewCnt=" + viewCnt +
                ", replyCnt=" + replyCnt +
                ", pageNo=" + pageNo +
                ", files=" + Arrays.toString(files) +
                ", fileCnt=" + fileCnt +
                '}';
    }
}
