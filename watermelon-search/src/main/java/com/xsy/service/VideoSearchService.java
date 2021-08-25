package com.xsy.service;

import com.xsy.entity.video.VideoVO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface VideoSearchService {

    public Map search(String key, Integer page, Integer pageSize) throws IOException;

}
