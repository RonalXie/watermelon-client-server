package com.xsy.entity.video;

import com.xsy.entity.user.UserUp;

import java.util.Date;

public class VideoDetailVO {
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 简介
     */
    private String intro;
    /**
     * up主id
     */
    private Integer uid;
    /**
     * 视频封面链接
     */
    private String cover;
    /**
     * 视频播放链接
     */
    private String link;
    /**
     * 分类id
     */
    private String category;
    private UserUp uploader;
    int palyCount;
    int likeCount;
    boolean liked;
    boolean disLiked;
    boolean favorite;
    private Date createdAt;
    private Date updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public UserUp getUploader() {
        return uploader;
    }

    public void setUploader(UserUp uploader) {
        this.uploader = uploader;
    }

    public int getPalyCount() {
        return palyCount;
    }

    public void setPalyCount(int palyCount) {
        this.palyCount = palyCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isDisLiked() {
        return disLiked;
    }

    public void setDisLiked(boolean disLiked) {
        this.disLiked = disLiked;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
