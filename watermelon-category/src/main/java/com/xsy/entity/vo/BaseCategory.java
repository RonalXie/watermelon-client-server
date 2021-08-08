package com.xsy.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) //返回数据不包含空数据
public class BaseCategory {

    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 父级分类id
     */
    private Integer parentId;

    public BaseCategory(){

    }

    public BaseCategory(Integer id, String name, Integer parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
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


}
