package com.xsy.controller;

import com.xsy.entity.Category;
import com.xsy.entity.CommonResult;
import com.xsy.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类(Category)表控制层
 *
 * @author makejava
 * @since 2021-08-06 16:36:15
 */
@RestController
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public Category selectOne(Integer id) {
        return this.categoryService.queryById(id);
    }

    @GetMapping("/categories")
    public CommonResult<List> categories(String token){
        List<Category> categories=categoryService.queryCategories();
        return new CommonResult<List>(200,"获取列表成功",categories);
    }


}
