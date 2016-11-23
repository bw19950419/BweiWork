package com.feicui.wnews.myapplication;

/**
 * Created by baiwei on 2016/11/17.
 */

public class NewsInfo {
    /*新闻*/
    private int nid;
    /*标题*/
    private String title;
    /*图标*/
    private String icon;
    /*摘要*/
    private String summary;
    /*网络连接*/
    private String link;
    /*新闻类型*/
    private int type;
    public NewsInfo(int nid, String title, String icon, String summary, String link, int type) {
        this.nid = nid;
        this.title = title;
        this.icon = icon;
        this.summary = summary;
        this.link = link;
        this.type = type;
    }



    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }



    @Override
    public String toString() {
        return "NewsInfo{" +
                "nid=" + nid +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", summary='" + summary + '\'' +
                ", link='" + link + '\'' +
                ", type=" + type +
                '}';
    }
}
