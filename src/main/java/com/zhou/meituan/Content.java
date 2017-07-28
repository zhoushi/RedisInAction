package com.zhou.meituan;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 * 美团技术博客实体bean
 */
public class Content {

    //标题
    private String title;

    //内容点击连接
    private String href;

    //作者
    private String author;

    //发布时间
    private String postTime;

    //发布简介
    private String introduction;

    //标签
    private List<Tag> tag;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Content{" +
                "title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", author='" + author + '\'' +
                ", postTime='" + postTime + '\'' +
                ", introduction='" + introduction + '\'' +
                ", tag=" + tag +
                '}' ;
    }
}
