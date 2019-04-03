package com.utils.tools.entity;

public class pageCount implements Comparable<pageCount>{
    private String page;
    private int count;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(pageCount o) {
        return o.getCount()-this.count==0? this.page.compareTo(o.getPage()): o.getCount()-this.count;
    }


}
