package com.xsy.entity.category;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 分类(Category)实体类
 *
 * @author makejava
 * @since 2021-08-06 16:36:14
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //返回数据不包含空数据
public class Category implements Serializable {
    private static final long serialVersionUID = 778945766986577252L;

    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 父级分类id
     */
    private Integer parentId;

    private Date createdAt;

    private Date updatedAt;



    private List<Category> children;

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }



}
