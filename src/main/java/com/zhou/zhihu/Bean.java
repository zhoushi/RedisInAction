package com.zhou.zhihu;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/28.
 */
public class Bean implements Serializable{

    private String title;

    private String author;

    private String content;

    private LocalDateTime updated;

    private Boolean canComment;

    private String commentPermission;

    private Integer commentCount;

    private Integer likeCount;

    private String  published;

    private Boolean isLiked;

    private String slug;

    private String rating;

    private String sourceUrl;


    private LocalDateTime publishedTime;

    private Map<String,String> links;

    private String url;

    private String titleImage;

    private String summary;

    private String href;

    private Map<String,String> meta;

    private String snapshotUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public Boolean getCanComment() {
        return canComment;
    }

    public void setCanComment(Boolean canComment) {
        this.canComment = canComment;
    }

    public String getCommentPermission() {
        return commentPermission;
    }

    public void setCommentPermission(String commentPermission) {
        this.commentPermission = commentPermission;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public LocalDateTime getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(LocalDateTime publishedTime) {
        this.publishedTime = publishedTime;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Map<String, String> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, String> meta) {
        this.meta = meta;
    }

    public String getSnapshotUrl() {
        return snapshotUrl;
    }

    public void setSnapshotUrl(String snapshotUrl) {
        this.snapshotUrl = snapshotUrl;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", updated=" + updated +
                ", canComment=" + canComment +
                ", commentPermission='" + commentPermission + '\'' +
                ", commentCount=" + commentCount +
                ", likeCount=" + likeCount +
                ", published='" + published + '\'' +
                ", isLiked=" + isLiked +
                ", slug='" + slug + '\'' +
                ", rating='" + rating + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", publishedTime=" + publishedTime +
                ", links=" + links +
                ", url='" + url + '\'' +
                ", titleImage='" + titleImage + '\'' +
                ", summary='" + summary + '\'' +
                ", href='" + href + '\'' +
                ", meta=" + meta +
                ", snapshotUrl='" + snapshotUrl + '\'' +
                '}' ;
    }
}
