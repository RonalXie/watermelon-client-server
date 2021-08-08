package com.xsy.entity.video;

import java.util.Date;
import java.io.Serializable;

/**
 * 评论(Comment)实体类
 *
 * @author makejava
 * @since 2021-08-08 22:24:41
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = -67947063419527344L;

    private Integer id;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 视频id
     */
    private Integer videoId;
    /**
     * 内容
     */
    private String content;
    /**
     * 父评论id
     */
    private Integer parentId;

    private Date createdAt;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }



}
