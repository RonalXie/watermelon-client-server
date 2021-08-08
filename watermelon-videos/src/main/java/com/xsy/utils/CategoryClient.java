package com.xsy.utils;


import com.xsy.entity.category.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "USER-CATEGORY")
public interface CategoryClient {

    @GetMapping("/selectOne")
    public Category selectOne(@RequestParam Integer id);

}


