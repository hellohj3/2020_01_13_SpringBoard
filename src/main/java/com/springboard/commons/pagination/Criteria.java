package com.springboard.commons.pagination;

public class Criteria {
    private int offset;
    private int page;
    private int articleCnt;

    public  Criteria() {
        this.page = 1;
        this.articleCnt = 7;
    }

    public void setPage(int page) {
        if (page <= 0) {
            this.page = 1;
            return;
        }
        this.page = page;
        this.offset = (this.page - 1) * this.articleCnt;
    }

    public int getPage() {
        return page;
    }

    public void setArticleCnt(int articleCnt) {
        if (articleCnt <= 0 || articleCnt > 100) {
            this.articleCnt = 7;
        }
    }

    public int getArticleCnt() {
        return this.articleCnt;
    }
}
