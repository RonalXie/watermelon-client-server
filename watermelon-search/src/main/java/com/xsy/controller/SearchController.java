package com.xsy.controller;


import com.xsy.entity.CommonResult;
import com.xsy.entity.video.VideoVO;
import com.xsy.service.VideoSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class SearchController {

    @Autowired
    private VideoSearchService videoSearchService;


    @GetMapping("/video/{page}/{pageSize}")
    public CommonResult<Map> search(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize, String key) throws IOException {

        Map<String,Object> result=videoSearchService.search(key,page,pageSize);

        return new CommonResult<Map>(200,"搜索成功!",result);
    }



}
