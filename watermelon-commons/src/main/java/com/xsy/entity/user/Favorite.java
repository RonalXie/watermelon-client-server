package com.xsy.entity.user;

import java.util.Date;
import java.io.Serializable;

/**
 * 收藏(Favorite)实体类
 *
 * @author makejava
 * @since 2021-08-08 19:21:25
 */
public class Favorite implements Serializable {
    private static final long serialVersionUID = 386785797238110365L;

    private Integer id;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 视频id
     */
    private Integer videoId;

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }



}
