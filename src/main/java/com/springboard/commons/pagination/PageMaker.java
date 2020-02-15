package com.springboard.commons.pagination;

public class PageMaker {
    private int totalCount;
    private int startPage;
    private int endPage;
    private int prev;
    private int next;

    private int displayPageNum = 5;

    private Criteria criteria;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getDisplayPageNum() {
        return displayPageNum;
    }

    public void setDisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    private void calcData() {
        endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum );
        startPage = (endPage - displayPageNum) + 1;

        int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getArticleCnt()));

        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        prev = ( (int) Math.floor(criteria.getPage() / (double) displayPageNum) - 1 ) * displayPageNum + 1;
        next = ( (int) Math.floor(criteria.getPage() / (double) displayPageNum) + 1 ) * displayPageNum + 1;
        next = ((tempEndPage - startPage) > (displayPageNum - 1)) ? next : -1;
    }

    @Override
    public String toString() {
        return "PageMaker{" +
                "totalCount=" + totalCount +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", prev=" + prev +
                ", next=" + next +
                ", displayPageNum=" + displayPageNum +
                ", criteria=" + criteria +
                '}';
    }
}
