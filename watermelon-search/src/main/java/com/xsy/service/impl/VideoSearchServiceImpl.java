package com.xsy.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xsy.entity.video.VideoVO;
import com.xsy.service.VideoSearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VideoSearchServiceImpl implements VideoSearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public Map search(String key, Integer page, Integer pageSize) throws IOException {

        Map<String,Object> result=new HashMap<>();

        //计算分页，起始为0
        int start=(page-1)*pageSize;
        //创建搜索对象
        SearchRequest searchRequest=new SearchRequest();
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();
        sourceBuilder.from(start).size(pageSize).query(QueryBuilders.multiMatchQuery(key,"title","intro"));

        //设置搜索索引 video，设置搜索类型video，设置搜索条件
        searchRequest.indices("video").types("video").source(sourceBuilder);

        SearchResponse searchResponse=null;

        //执行搜索
        searchResponse=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //获取符合条件总数

        long total=searchResponse.getHits().totalHits;

        //封装结果
        if (total>0){
            result.put("total",total);

            //获取符合条件文档数组
            SearchHit[] hits=searchResponse.getHits().getHits();
            //创建list集合
            List<VideoVO> videoVOS=new ArrayList<>();
            //遍历符合条件每个文档封装成VideoVO对象
            for (SearchHit hit: hits) {

                //获取文件字符串表现形式（json）
                String sourceString=hit.getSourceAsString();
                //通过jackson将json转化为videoVO对象
                VideoVO videoVO=new ObjectMapper().readValue(sourceString,VideoVO.class);
                //设置VideoVo文档id
                videoVO.setId(Integer.parseInt(hit.getId()));
                //放入集合
                videoVOS.add(videoVO);
            }
            result.put("item",videoVOS);
        }

        return result;
    }
}
