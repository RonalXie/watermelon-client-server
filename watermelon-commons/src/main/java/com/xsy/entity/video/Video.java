package com.xsy.entity.video;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频(Video)实体类
 *
 * @author makejava
 * @since 2021-08-06 16:38:13
 */
public class Video implements Serializable {
    private static final long serialVersionUID = -81827551456454873L;

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
    private Integer categoryId;

    private String createdAt;

    private String updatedAt;


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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", intro='" + intro + '\'' +
                ", uid=" + uid +
                ", cover='" + cover + '\'' +
                ", link='" + link + '\'' +
                ", categoryId=" + categoryId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
