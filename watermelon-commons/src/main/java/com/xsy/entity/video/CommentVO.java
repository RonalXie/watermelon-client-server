package com.xsy.entity.video;

import com.xsy.entity.user.User;
import com.xsy.entity.user.UserUp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 评论(Comment)实体类
 *
 * @author makejava
 * @since 2021-08-08 22:24:41
 */
public class CommentVO implements Serializable {
    private static final long serialVersionUID = -67947063419527344L;

    private Integer id;
    private String content;
    private Integer parentId;
    private Date createdAt;
    private UserUp userUp;
    private List<CommentVO> comments;



    public UserUp getUserUp() {
        return userUp;
    }

    public void setUserUp(UserUp userUp) {
        this.userUp = userUp;
    }

    public List<CommentVO> getComments() {
        return comments;
    }

    public void setComments(List<CommentVO> comments) {
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
